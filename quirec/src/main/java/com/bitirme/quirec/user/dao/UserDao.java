package com.bitirme.quirec.user.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.quirec.user.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends BaseDao<User> {

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.email = (:userInfo) or u.username = (:userInfo)")
    User findUserByEmailOrUsername(@Param("userInfo") String userInfo);

}
