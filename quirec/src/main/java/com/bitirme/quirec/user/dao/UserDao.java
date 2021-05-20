package com.bitirme.quirec.user.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.quirec.questionnarie.model.CategoryType;
import com.bitirme.quirec.user.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends BaseDao<User> {

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.email = (:userInfo) or u.username = (:userInfo)")
    User findUserByEmailOrUsername(@Param("userInfo") String userInfo);

    @Query(value = "SELECT DISTINCT category_type FROM quirec.categories as c " +
            " INNER JOIN quirec.user_categories uc on c.id = uc.category_id " +
            "WHERE uc.user_id = (:userId)", nativeQuery = true)
    List<CategoryType> findUserCategories(long userId);

}