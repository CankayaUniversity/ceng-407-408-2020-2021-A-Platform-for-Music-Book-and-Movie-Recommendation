package com.bitirme.quirec.questionnarie.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.model.CategoryType;

import java.util.List;

public interface CategoryDao extends BaseDao<Categories> {

    List<Categories> findCategoriesByCategoryType(CategoryType type);

    Categories findCategoriesByCategoryTypeAndName(CategoryType type, String name);

    Categories findCategoriesByCategoryTypeAndOriginalId(CategoryType type, long originalId);

}
