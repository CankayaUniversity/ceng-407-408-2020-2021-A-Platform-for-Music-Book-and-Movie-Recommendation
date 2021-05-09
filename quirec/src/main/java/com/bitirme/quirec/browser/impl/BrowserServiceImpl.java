package com.bitirme.quirec.browser.impl;

import com.bitirme.dataset.dao.BookDao;
import com.bitirme.dataset.dao.MovieDao;
import com.bitirme.dataset.dao.MusicDao;
import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Movie;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.browser.service.BrowserService;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.recommendation.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BrowserServiceImpl implements BrowserService {

    @Autowired
    MusicDao musicDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    MovieDao movieDao;

    @Override
    public List<Music> getMusic() {
        return musicDao.findAll();
    }

    @Override
    public List<Book> getBook() {
        return bookDao.findAll();
    }

    @Override
    public List<Movie> getMovie() {
        return movieDao.findAll();
    }

    @Override
    public List<Music> musicSearch(String searchType, String searchDetail) {
        return null;
    }

    @Override
    public List<Book> bookSearch(String searchType, String searchDetail) {
        return null;
    }

    @Override
    public List<Movie> movieSearch(String searchType, String searchDetail) {
        return null;
    }
}
