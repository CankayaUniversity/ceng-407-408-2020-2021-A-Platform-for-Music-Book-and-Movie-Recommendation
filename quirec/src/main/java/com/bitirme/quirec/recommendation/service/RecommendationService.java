package com.bitirme.quirec.recommendation.service;

import com.bitirme.quirec.questionnarie.model.CategoryType;
import com.bitirme.quirec.recommendation.model.Recommendation;

public interface RecommendationService {

    Recommendation get(long userId);

    void rate(CategoryType type, double rate);

}
