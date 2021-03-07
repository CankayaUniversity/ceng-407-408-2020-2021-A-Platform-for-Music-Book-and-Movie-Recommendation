package com.bitirme.quirec.user.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bitirme.quirec.user.dao.UserDao;
import com.bitirme.quirec.user.model.User;
import com.bitirme.quirec.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    private final String SECRET = "secretKey";

    private BCrypt bCrypt;

    private BCryptPasswordEncoder bCryptEncoder;

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
            newUser.setPassword(bCryptEncoder.encode(user.getPassword()));

            return userDao.saveAndFlush(newUser);
        }

        return null; //aynı emaille biri kayıtlı
    }

    @Override
    public String login(String userInfo, String password) {
        User userControl = userDao.findUserByEmailOrUsername(userInfo, userInfo);

        if (userControl != null) {
            if (bCryptEncoder.matches(password, userControl.getPassword())) {
                return JWT.create()
                        .withSubject(userControl.getUsername())
                        .sign(Algorithm.HMAC512(SECRET.getBytes()));
            }

            return "mismatch"; //userın girdiği password dbdeki password ile örtüşmedi
        }

        return "user bulunamadı";
    }

    @Override
    public User update(long userId, String newPassword) {
        User update = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        update.setPassword(bCryptEncoder.encode(newPassword));

        return userDao.saveAndFlush(update);
    }

    @Override
    public void delete(long userId) {
        userDao.deleteById(userId);
    }
}
