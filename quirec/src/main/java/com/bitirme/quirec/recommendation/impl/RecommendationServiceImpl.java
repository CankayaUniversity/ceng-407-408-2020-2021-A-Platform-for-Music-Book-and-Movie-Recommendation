package com.bitirme.quirec.recommendation.impl;

import com.bitirme.api.JSONParser.JSONParsingService;
import com.bitirme.dataset.dao.MusicDao;
import com.bitirme.dataset.model.Movie;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.questionnarie.model.CategoryType;
import com.bitirme.quirec.recommendation.dao.RatingDao;
import com.bitirme.quirec.recommendation.dao.RecommendationDao;
import com.bitirme.quirec.recommendation.model.Rating;
import com.bitirme.quirec.recommendation.model.Recommendation;
import com.bitirme.quirec.recommendation.service.RecommendationService;
import com.bitirme.quirec.user.dao.UserDao;
import com.bitirme.quirec.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

    @Autowired
    JSONParsingService parsingService;

    @Autowired
    MusicDao musicDao;

    @Override
        public Recommendation get(long userId) throws IOException {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        Recommendation recommendation = new Recommendation();

        String url = "http://127.0.0.1:5000/"+ userId;

        String datasetPath = "D:\\\\Datasets\\top10s.csv";

        try (PrintWriter writer = new PrintWriter(datasetPath)) {
            List<Music> musicList = musicDao.findAll(Sort.by("id"));

            writer.println("id" + "," + "title");

            musicList.forEach(
                    music -> writer.println(music.getId() + "," + music.getTitle())
            );

            writer.flush();

            String ids = parsingService.postForRecommendation(url, datasetPath);

            String lines[] = ids.split("\n");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        if (user.getCategories().contains(CategoryType.MOVIE)) {
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
