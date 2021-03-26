package com.bitirme.quirec.questionnarie.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.model.CategoryType;

public interface CategoryDao extends BaseDao<Categories> {

    Categories findCategoriesByCategoryTypeAndName(CategoryType type, String name);

    Categories findCategoriesByCategoryTypeAndOriginalId(CategoryType type, long originalId);

}
