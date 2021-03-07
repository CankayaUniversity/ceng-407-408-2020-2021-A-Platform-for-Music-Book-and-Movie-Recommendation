package com.bitirme.quirec.questionnarie.controller;

import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/quirec-api/questionnarie")
public class QuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    //anket sayfasında mevcut tüm kategorilerin görülmesi için NOT: Kategorileri dbye elle kaydedeceğiz
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Categories>> getCategories() {
        return new ResponseEntity<>(
                questionnaireService.getCategories(),
                HttpStatus.OK
        );
    }

    //user profilden güncellerken anketi görebilmek için
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Set<Categories>> get(@PathVariable("id") long userId) {
        return new ResponseEntity<>(
                questionnaireService.get(userId),
                HttpStatus.OK
        );
    }

    //user anketi ilk kez doldurduğunda çalışacak
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public ResponseEntity<Set<Categories>> create(@PathVariable("id") long userId, @RequestBody List<Categories> questionnaire) {
        return new ResponseEntity<>(
                questionnaireService.create(userId, questionnaire),
                HttpStatus.OK
        );
    }

    //anket update
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Set<Categories>> update(@PathVariable("id") long userId, @RequestBody List<Categories> questionnaire) {
        return new ResponseEntity<>(
                questionnaireService.update(userId, questionnaire),
                HttpStatus.OK
        );
    }

}
