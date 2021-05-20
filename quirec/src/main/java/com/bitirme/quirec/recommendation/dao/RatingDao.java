package com.bitirme.quirec.recommendation.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.quirec.questionnarie.model.CategoryType;
import com.bitirme.quirec.recommendation.model.Rating;

public interface RatingDao extends BaseDao<Rating> {

    Rating findRatingByCategoryType(CategoryType categoryType);

}