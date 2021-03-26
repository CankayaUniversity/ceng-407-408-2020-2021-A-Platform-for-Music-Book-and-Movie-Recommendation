package com.bitirme.quirec.recommendation.impl;

import com.bitirme.dataset.model.Movie;
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
import java.util.ArrayList;
import java.util.List;

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

        Recommendation recommendation = new Recommendation();

        if (user.getCategories().contains(CategoryType.MOVIE)) {
            /*
        * user = np.array([2301 for i in range(len(movie_data))])
            predictions = model2.predict([user, movie_data])

        predictions = np.array([a[0] for a in predictions])

        recommended_movie_ids = (-predictions).argsort()[:25]


        movies = pd.read_csv('new_movie_big.csv')


        m=movies[movies['id'].isin(recommended_movie_ids)]
        *
        * */

            List<Movie> m = new ArrayList<>();

            m.forEach(
                    mov -> {
                        recommendation.getMovieRecommendations().add(mov);
                    }
            );
        }

        if (user.getCategories().contains(CategoryType.BOOK)) {
            //Kitap recom yap
        }

        if (user.getCategories().contains(CategoryType.MUSIC)) {
            //MUSIC recom yap
        }

        return recommendation;
    }

    @Override
    public void rate(CategoryType type, double rate) {
        Rating rating = ratingDao.findRatingByCategoryType(type);

        rating.setRate(rating.getRate() + rate);
        rating.setVoteNumber(rating.getVoteNumber() + 1);

        ratingDao.saveAndFlush(rating);
    }

}
