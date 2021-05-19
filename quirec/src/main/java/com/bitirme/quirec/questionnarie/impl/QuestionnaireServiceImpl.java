package com.bitirme.quirec.questionnarie.impl;

import com.bitirme.dataset.dao.BookDao;
import com.bitirme.dataset.dao.MovieDao;
import com.bitirme.dataset.dao.MusicDao;
import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Movie;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.questionnarie.dao.CategoryDao;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.model.CategoryType;
import com.bitirme.quirec.questionnarie.model.Questionnaire;
import com.bitirme.quirec.questionnarie.service.QuestionnaireService;
import com.bitirme.quirec.user.dao.UserDao;
import com.bitirme.quirec.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    UserDao userDao;

    @Autowired
    MusicDao musicDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    MovieDao movieDao;

    @Override
    public List<Categories> getCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Set<Categories> get(long userId) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        return user.getCategories();
    }

    @Override
    public void questionnaire(long userId, List<Questionnaire> questionnaire) throws Exception {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        String userMusicRatings = "D:\\\\Datasets\\music_ratings.csv";
        PrintWriter musicWriter = new PrintWriter(new BufferedWriter(new FileWriter(userMusicRatings, true)));

        String userBookRatings = "D:\\\\Datasets\\book_ratings.csv";
        PrintWriter bookWriter = new PrintWriter(new BufferedWriter(new FileWriter(userBookRatings, true)));

        String userMovieRatings = "D:\\\\Datasets\\movie_ratings.csv";
        PrintWriter movieWriter = new PrintWriter(new BufferedWriter(new FileWriter(userMovieRatings, true)));

        questionnaire.forEach(
                questionnaireElement -> {
                    Categories category = categoryDao.findById(questionnaireElement.getCategoryId()).orElseThrow(
                            () -> new EntityNotFoundException("category")
                    );

                    user.getCategories().add(category);

                    if(category.getCategoryType() == CategoryType.MUSIC) {
                        Music music = musicDao.findById(questionnaireElement.getItemId()).orElseThrow(
                                () -> new EntityNotFoundException("music")
                        );

                        musicWriter.println(music.getId() + "," + userId + "," + questionnaireElement.getUserRating());
                    }

                    else if(category.getCategoryType() == CategoryType.BOOK) {
                        Book book = bookDao.findById(questionnaireElement.getItemId()).orElseThrow(
                                () -> new EntityNotFoundException("book")
                        );

                        bookWriter.println(book.getId() + "," + userId + "," + questionnaireElement.getUserRating());
                    }

                    else if(category.getCategoryType() == CategoryType.MOVIE) {
                        Movie movie = movieDao.findById(questionnaireElement.getItemId()).orElseThrow(
                                () -> new EntityNotFoundException("movie")
                        );

                        movieWriter.append(movie.getId() + "," + userId + "," + questionnaireElement.getUserRating());
                    }
                }
        );

        musicWriter.flush();
        bookWriter.flush();
        movieWriter.flush();

        userDao.saveAndFlush(user);
    }
}
