package com.bitirme.quirec.recommendation.impl;

import com.bitirme.api.JSONParser.JSONParsingService;
import com.bitirme.dataset.dao.BookDao;
import com.bitirme.dataset.dao.MovieDao;
import com.bitirme.dataset.dao.MusicDao;
import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Movie;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.questionnarie.model.CategoryType;
import com.bitirme.quirec.recommendation.dao.RatingDao;
import com.bitirme.quirec.recommendation.model.Rating;
import com.bitirme.quirec.recommendation.model.Recommendation;
import com.bitirme.quirec.recommendation.service.RecommendationService;
import com.bitirme.quirec.user.dao.UserDao;
import com.bitirme.quirec.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    UserDao userDao;

    @Autowired
    RatingDao ratingDao;

    @Autowired
    JSONParsingService parsingService;

    @Autowired
    MusicDao musicDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    MovieDao movieDao;

    @Override
        public Recommendation get(long userId) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        Recommendation recommendation = new Recommendation();

        //TODO: containsler çalışmıyor
        if (user.getCategories().contains(CategoryType.MUSIC)) {
            String musicUrl = "http://127.0.0.1:5000/music/" + userId;

            String musicIds = parsingService.getForRecommendation(musicUrl);
            String musics[] = musicIds.split("\n");

            for (int i = 0; i < musics.length; i++) {
                String musicId = musics[i].replaceAll("\\s+","");
                Music music = musicDao.findById(Long.valueOf(musicId)).orElseThrow(
                        () -> new EntityNotFoundException("music")
                );

                recommendation.getMusicRecommendations()[i] = music;
            }
        }

        if (user.getCategories().contains(CategoryType.BOOK)) {
            String url = "http://127.0.0.1:5000/books/"+ userId;

            String bookIds = parsingService.getForRecommendation(url);
            String books[] = bookIds.split("\n");

            for (int i = 0; i < books.length; i++) {
                String bookId = books[i].replaceAll("\\s+","");
                Book book = bookDao.findById(Long.valueOf(bookId)).orElseThrow(
                        () -> new EntityNotFoundException("book")
                );

                recommendation.getBookRecommendations()[i] = book;
            }
        }

        //if (user.getCategories().contains(CategoryType.MOVIE)) {
            String url = "http://127.0.0.1:5000/movie/"+ userId;

            String movieIds = parsingService.getForRecommendation(url);
            String movies[] = movieIds.split("\n");

            for (int i = 0; i < movies.length; i++) {
                String movieId = movies[i].replaceAll("\\s+","");
                Movie movie = movieDao.findById(Long.valueOf(movieId)).orElseThrow(
                        () -> new EntityNotFoundException("movie")
                );

                recommendation.getMovieRecommendations()[i] = movie;
            }
        //}

        return recommendation;
    }

    @Override
    public void rate(long userId, CategoryType type, double rate) {
        Rating rating = ratingDao.findRatingByCategoryType(type);

        rating.setRate(rating.getRate() + rate);
        rating.setVoteNumber(rating.getVoteNumber() + 1);

        ratingDao.saveAndFlush(rating);
    }

}
