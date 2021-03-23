package com.bitirme.quirec.recommendation.controller;

import com.bitirme.quirec.questionnarie.model.CategoryType;
import com.bitirme.quirec.recommendation.model.Recommendation;
import com.bitirme.quirec.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/quirec-api/recommendation")
public class RecommendationController {

    @Autowired
    RecommendationService recommendationService;

    //recommendations page
    //get recommendations for user with id "id"
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Recommendation> get(@PathVariable("id") long userId) {
        return new ResponseEntity<>(
                recommendationService.get(userId),
                HttpStatus.OK
        );
    }

    //rate recommendation accuracy
    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public ResponseEntity<Void> rate(@RequestBody CategoryType type, int rate) {
        recommendationService.rate(type, rate);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

}
