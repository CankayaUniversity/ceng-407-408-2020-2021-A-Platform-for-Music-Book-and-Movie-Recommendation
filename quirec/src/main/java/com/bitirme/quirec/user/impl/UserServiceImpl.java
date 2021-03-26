package com.bitirme.quirec.user.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bitirme.quirec.user.dao.UserDao;
import com.bitirme.quirec.user.model.LoginReturn;
import com.bitirme.quirec.user.model.User;
import com.bitirme.quirec.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    private final String SECRET = "secretKey";

    private final String salt = BCrypt.gensalt("$2b", 5);

    @Override
    public User get(long userId) {
        return userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );
    }

    @Override
    public User register(User user) {
        User emailControl = userDao.findUserByEmail(user.getEmail());

        if (emailControl == null) {
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());

            newUser.setPassword(BCrypt.hashpw(user.getPassword(), salt));

            return userDao.saveAndFlush(newUser);
        }

        return null; //aynı emaille biri kayıtlı
    }

    @Override
    public LoginReturn login(User user) {
        String userInfo = user.getEmail() == null ? user.getUsername() : user.getEmail();
        User userControl = userDao.findUserByEmailOrUsername(userInfo);

        LoginReturn loginReturn = new LoginReturn();

        if (userControl != null) {
            if (BCrypt.checkpw(user.getPassword(), userControl.getPassword())) {
                String accessToken = JWT.create()
                        .withSubject(userControl.getUsername())
                        .sign(Algorithm.HMAC512(SECRET.getBytes()));

                loginReturn.setAccessToken(accessToken);
                loginReturn.setUserId(userControl.getId());
            }

            else loginReturn.setAccessToken("mismatch");
        }

        else loginReturn.setAccessToken("user not found");
        return loginReturn;
    }

    @Override
    public User update(long userId, String newPassword) {
        User update = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        update.setPassword(BCrypt.hashpw(newPassword, salt));

        return userDao.saveAndFlush(update);
    }

    @Override
    public void delete(long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public void forgotPassword(User user){
        User emailControl = userDao.findUserByEmail(user.getEmail());

        if (emailControl != null){
            //TODO: mail service
        }

    }
}
