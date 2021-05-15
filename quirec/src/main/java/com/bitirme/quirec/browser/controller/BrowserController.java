package com.bitirme.quirec.browser.controller;

import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Movie;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.browser.service.BrowserService;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.recommendation.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/quirec-api/browse")
public class BrowserController {

    @Autowired
    BrowserService browserService;

    @RequestMapping(value = "/music", method = RequestMethod.GET)
    public ResponseEntity<List<Music>> getMusic() {
        return new ResponseEntity<>(
                browserService.getMusic(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBook() {
        return new ResponseEntity<>(
                browserService.getBook(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getMovie() {
        return new ResponseEntity<>(
                browserService.getMovie(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/musicSearch/{searchDetail}", method = RequestMethod.GET)
    public ResponseEntity<List<Music>> musicSearch(@PathVariable String searchDetail) {
        return new ResponseEntity<>(
                browserService.musicSearch(searchDetail),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/bookSearch/{searchDetail}", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> bookSearch(@PathVariable String searchDetail) {
        return new ResponseEntity<>(
                browserService.bookSearch(searchDetail),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/movieSearch/{searchDetail}", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> movieSearch(@PathVariable String searchDetail) {
        return new ResponseEntity<>(
                browserService.movieSearch(searchDetail),
                HttpStatus.OK
        );
    }

}
