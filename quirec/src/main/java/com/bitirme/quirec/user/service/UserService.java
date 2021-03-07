package com.bitirme.quirec.user.service;

import com.bitirme.quirec.user.model.User;

public interface UserService {

    User get(long userId);

    User register(User user);

    String login(String userInfo, String password);

    User update(long userId, String newPassword);

    void delete(long userId);

}
