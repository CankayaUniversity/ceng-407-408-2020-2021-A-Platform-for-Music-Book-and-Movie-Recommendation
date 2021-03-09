package com.bitirme.quirec.browser.service;

import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Movie;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.recommendation.model.Recommendation;

import java.util.List;

public interface BrowserService {

    List<Music> getMusic();

    List<Book> getBook();

    List<Movie> getMovie();

    Recommendation search(Categories searchCategory);

    Recommendation detailedSearch(Categories searchInfo);

}
