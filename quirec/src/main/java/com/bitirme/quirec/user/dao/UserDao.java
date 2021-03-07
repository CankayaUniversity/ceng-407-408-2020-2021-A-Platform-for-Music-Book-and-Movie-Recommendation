package com.bitirme.quirec.user.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.quirec.user.model.User;

public interface UserDao extends BaseDao<User> {

    User findUserByEmail(String email);

    User findUserByEmailOrUsername(String email, String username);

}
