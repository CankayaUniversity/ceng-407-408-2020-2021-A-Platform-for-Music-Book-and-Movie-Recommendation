package com.bitirme.quirec.browser.controller;

import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Movie;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.browser.service.BrowserService;
import com.bitirme.quirec.questionnarie.model.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/quirec-api/browse")
public class BrowserController {

    @Autowired
    BrowserService browserService;

    //dbdeki tüm şarkılar
    @RequestMapping(value = "/music", method = RequestMethod.GET)
    public ResponseEntity<List<Music>> getMusic() {
        return new ResponseEntity<>(
                browserService.getMusic(),
                HttpStatus.OK
        );
    }

    //dbdeki tüm kitaplar
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBook() {
        return new ResponseEntity<>(
                browserService.getBook(),
                HttpStatus.OK
        );
    }

    //dbdeki tüm filmler
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getMovie() {
        return new ResponseEntity<>(
                browserService.getMovie(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    //TODO: return type değişecek
    public ResponseEntity<Categories> search(Categories searchCategory) {
        return new ResponseEntity<>(
                browserService.search(searchCategory),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/search/detail", method = RequestMethod.GET)
    //TODO: return type değişecek
    public ResponseEntity<Categories> detailedSearch(Categories searchInfo) {
        return new ResponseEntity<>(
                browserService.detailedSearch(searchInfo),
                HttpStatus.OK
        );
    }

}
