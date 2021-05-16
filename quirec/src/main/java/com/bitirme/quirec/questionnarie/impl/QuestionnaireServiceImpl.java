package com.bitirme.quirec.questionnarie.impl;

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
    public void create(long userId, List<Questionnaire> questionnaire) throws Exception {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        if(user.getCategories() == null) {
            String userMusicRatings = "D:\\\\Datasets\\user" + userId + "music.csv";
            PrintWriter musicWriter = new PrintWriter(userMusicRatings);
            musicWriter.println("user_id" + "," + "music_id" + "rating");

            String userBookRatings = "D:\\\\Datasets\\user" + userId + "book.csv";
            PrintWriter bookWriter = new PrintWriter(userBookRatings);
            bookWriter.println("user_id" + "," + "book_id" + "rating");

            String userMovieRatings = "D:\\\\Datasets\\user" + userId + "movie.csv";
            PrintWriter movieWriter = new PrintWriter(userMovieRatings);
            movieWriter.println("user_id" + "," + "movie_id" + "rating");

            questionnaire.forEach(
                    questionnaireElement -> {
                        Categories category = categoryDao.findById(questionnaireElement.getItemId()).orElseThrow(
                                () -> new EntityNotFoundException("category")
                        );

                        user.getCategories().add(category);

                        if(category.getCategoryType() == CategoryType.MUSIC)
                            musicWriter.println(userId + "," + category.getId() + "," + questionnaireElement.getUserRating());

                        else if(category.getCategoryType() == CategoryType.BOOK)
                            bookWriter.println(userId + "," + category.getId() + "," + questionnaireElement.getUserRating());

                        else if(category.getCategoryType() == CategoryType.MOVIE)
                            movieWriter.println(userId + "," + category.getId() + "," + questionnaireElement.getUserRating());
                    }
            );

            musicWriter.flush();
            bookWriter.flush();
            movieWriter.flush();

            userDao.saveAndFlush(user);
        }

        else throw new Exception("user already has a questionnaire");
    }

    @Override
    public Set<Categories> update(long userId, List<Categories> questionnaire) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );
        //TODO: CopyFrom yaz
        return user.getCategories(); //TODO: save and flush eklenecek
    }
}
