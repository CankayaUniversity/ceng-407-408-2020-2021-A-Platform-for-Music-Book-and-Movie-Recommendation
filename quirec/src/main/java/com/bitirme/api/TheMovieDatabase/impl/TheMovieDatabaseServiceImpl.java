package com.bitirme.api.TheMovieDatabase.impl;

import com.bitirme.api.JSONParser.JSONParsingService;
import com.bitirme.api.TheMovieDatabase.service.TheMovieDatabaseService;
import com.bitirme.dataset.dao.MovieDao;
import com.bitirme.dataset.model.Movie;
import com.bitirme.quirec.questionnarie.dao.CategoryDao;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.model.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
@Transactional
public class TheMovieDatabaseServiceImpl implements TheMovieDatabaseService {

    @Autowired
    JSONParsingService parsingService;

    @Autowired
    MovieDao movieDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void getMovies() {
        String getMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=7f613a475981323801d5497ba77b29b5&page=1";
        LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> resource = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>) parsingService.get(getMoviesUrl);

        ArrayList<LinkedHashMap<String, Object>> movies = resource.get("results");

        for(LinkedHashMap<String, Object> movie : movies){
            int resourceId = (int) movie.get("id");
            Movie existingMovie = movieDao.findMoviesByResourceId(resourceId);

            if(existingMovie == null){
                LinkedHashMap<String, ArrayList> categories = (LinkedHashMap<String, ArrayList>) movie.get("genre_ids");

                if(categories != null){
                    Movie newMovie = new Movie();

                    newMovie.setResourceId(resourceId);
                    newMovie.setTitle((String) movie.get("title"));
                    newMovie.setPoster((String) movie.get("poster_path"));
                    newMovie.setLanguage((String) movie.get("original_language"));


                }



            }
        }
    }




}
