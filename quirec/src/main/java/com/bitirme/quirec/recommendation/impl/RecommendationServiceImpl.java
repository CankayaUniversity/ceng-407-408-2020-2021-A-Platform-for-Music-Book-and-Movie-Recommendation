package com.bitirme.quirec.recommendation.impl;

import com.bitirme.quirec.questionnarie.model.CategoryType;
import com.bitirme.quirec.recommendation.dao.RatingDao;
import com.bitirme.quirec.recommendation.dao.RecommendationDao;
import com.bitirme.quirec.recommendation.model.Rating;
import com.bitirme.quirec.recommendation.model.Recommendation;
import com.bitirme.quirec.recommendation.service.RecommendationService;
import com.bitirme.quirec.user.dao.UserDao;
import com.bitirme.quirec.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    UserDao userDao;

    @Autowired
    RecommendationDao recommendationDao;

    @Autowired
    RatingDao ratingDao;

    @Override
    public Recommendation get(long userId) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        user.getCategories(); //user'ın ankette seçtiği tüm türler geliyor örn "müzik: rock, kitap: romantik, film: aksiyon"

        Recommendation recommendation = new Recommendation();
        //dataset ile user categories türler kıyaslanarak alttaki gibi addler yapılacak
        //recommendation.getMovieRecommendations().add(Movie);

        return recommendation;
    }

    @Override
    public Recommendation detail(long recommendationId) {
        return recommendationDao.findById(recommendationId).orElseThrow(
                () -> new EntityNotFoundException("detail")
        );
    }

    @Override
    public void rate(CategoryType type, double rate) {
        Rating rating = ratingDao.findRatingByCategoryType(type);

        rating.setRate(rating.getRate() + rate);
        rating.setVoteNumber(rating.getVoteNumber() + 1);

        ratingDao.saveAndFlush(rating);
    }

}
