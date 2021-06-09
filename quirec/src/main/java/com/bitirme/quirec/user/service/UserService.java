package com.bitirme.quirec.user.service;

import com.bitirme.quirec.user.model.LoginReturn;
import com.bitirme.quirec.user.model.User;

public interface UserService {

    User get(long userId);

    User register(User user) throws Exception;

    LoginReturn login(User user) throws Exception;

    User changePassword(long userId, String passwords) throws Exception;

    void delete(long userId);

    void forgotPassword(User user) throws Exception;

    void resetPassword(User user) throws Exception;

}