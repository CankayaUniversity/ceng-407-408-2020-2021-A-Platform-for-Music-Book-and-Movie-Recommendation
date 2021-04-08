package com.bitirme.api.TheMovieDatabase.impl;

import com.bitirme.api.JSONParser.JSONParsingService;
import com.bitirme.api.TheMovieDatabase.dao.OriginalCategoriesDao;
import com.bitirme.api.TheMovieDatabase.model.OriginalCategories;
import com.bitirme.api.TheMovieDatabase.service.TheMovieDatabaseService;
import com.bitirme.dataset.dao.MovieDao;
import com.bitirme.dataset.model.Movie;
import com.bitirme.quirec.questionnarie.dao.CategoryDao;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.model.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    @Autowired
    OriginalCategoriesDao originalCategoriesDao;

    @Override
    public void getMovies() {
        int page = (int) ((movieDao.count() / 20) + 1);

        String getMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=7f613a475981323801d5497ba77b29b5&page={page}";
        LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> resource = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>) parsingService.getForPage(getMoviesUrl, page);

        ArrayList<LinkedHashMap<String, Object>> movies = resource.get("results");

        for(LinkedHashMap<String, Object> movie : movies){
            int resourceId = (int) movie.get("id");
            Movie existingMovie = movieDao.findMoviesByResourceId(resourceId);

            if(existingMovie == null){
                ArrayList<Integer> categories = (ArrayList<Integer>) movie.get("genre_ids");

                if(categories != null){
                    Movie newMovie = new Movie();

                    newMovie.setResourceId(resourceId);
                    newMovie.setTitle((String) movie.get("title"));
                    newMovie.setPoster((String) movie.get("poster_path"));
                    newMovie.setLanguage((String) movie.get("original_language"));

                    categories
                            .forEach(
                                    categoryId -> {
                                        OriginalCategories originalC = originalCategoriesDao.findByOriginalId(categoryId);

                                        if (originalC != null) {
                                            Categories c = categoryDao.findCategoriesByCategoryTypeAndOriginalId(CategoryType.MOVIE, categoryId);
                                            newMovie.getCategories().add(c);
                                            movieDao.saveAndFlush(newMovie);
                                        }

                                        else
                                            throw new EntityNotFoundException("original category not found");
                                    }
                            );
                }
            }
        }
    }

    private void getOriginalCategories() {
        String getCategoriesUrl = "https://api.themoviedb.org/3/genre/movie/list?api_key=7f613a475981323801d5497ba77b29b5";
        LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> resource = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> ) parsingService.get(getCategoriesUrl);

        ArrayList<LinkedHashMap<String, Object>> categories = resource.get("genres");

        categories
                .forEach(
                    category -> {
                        int id = Integer.parseInt(String.valueOf(category.get("id")));
                        OriginalCategories originalC = originalCategoriesDao.findByOriginalId(id);

                        if(originalC == null) {
                            OriginalCategories newC = new OriginalCategories();
                            newC.setOriginalId(id);
                            newC.setName(category.get("name").toString());

                            Categories c = new Categories();
                            c.setCategoryType(CategoryType.MOVIE);
                            c.setName(newC.getName());
                            c.setOriginalId(id);

                            originalCategoriesDao.saveAndFlush(newC);
                            categoryDao.saveAndFlush(c);
                        }
                    }
        );
    }
}
