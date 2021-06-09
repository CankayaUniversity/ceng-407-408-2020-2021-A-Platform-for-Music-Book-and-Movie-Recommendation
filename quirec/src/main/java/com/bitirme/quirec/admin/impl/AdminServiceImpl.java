package com.bitirme.quirec.admin.impl;

import com.bitirme.api.Deezer.service.DeezerService;
import com.bitirme.api.GoogleBooks.service.GoogleBooksService;
import com.bitirme.api.JSONParser.JSONParsingService;
import com.bitirme.api.TheMovieDatabase.service.TheMovieDatabaseService;
import com.bitirme.quirec.admin.service.AdminService;
import com.bitirme.quirec.recommendation.dao.RatingDao;
import com.bitirme.quirec.recommendation.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    RatingDao ratingDao;

    @Autowired
    GoogleBooksService booksService;

    @Autowired
    TheMovieDatabaseService movieDatabaseService;

    @Autowired
    DeezerService deezerService;

    @Autowired
    JSONParsingService parsingService;

    @Override
    public List<Rating> getRatings() {
        return ratingDao.findAll();
    }

    @Override
    public void databaseUpdate(){
        booksService.getBooks();
        movieDatabaseService.getMovies();
        deezerService.getMusic();
        String url = "http://127.0.0.1:5001";
        parsingService.get(url);
    }

    @Override
    public void modelRetrain() {
        String url = "http://127.0.0.1:5002";
        parsingService.get(url);
    }

}
