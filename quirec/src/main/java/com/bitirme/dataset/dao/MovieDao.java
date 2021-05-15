package com.bitirme.dataset.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Movie;
import com.bitirme.quirec.questionnarie.model.Categories;

import java.util.List;

public interface MovieDao extends BaseDao<Movie> {

    Movie findMoviesByResourceId(int resourceId);

    List<Movie> findMovieByCategories(Categories category);

    List<Movie> findAllByTitleLike(String title);

}
