package com.bitirme.api.TheMovieDatabase.dao;

import com.bitirme.api.TheMovieDatabase.model.OriginalCategories;
import com.bitirme.base.dao.BaseDao;

public interface OriginalCategoriesDao extends BaseDao<OriginalCategories> {

    OriginalCategories findByOriginalId(int id);

}
