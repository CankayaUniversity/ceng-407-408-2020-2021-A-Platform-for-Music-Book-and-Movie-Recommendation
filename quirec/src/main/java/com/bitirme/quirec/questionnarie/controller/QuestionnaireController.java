package com.bitirme.quirec.questionnarie.controller;

import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.model.Questionnaire;
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

@Controller
@RequestMapping("/quirec-api/questionnaire")
public class QuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Categories>> getCategories() {
        return new ResponseEntity<>(
                questionnaireService.getCategories(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> questionnaire(@PathVariable("id") long userId, @RequestBody List<Questionnaire> questionnaire) throws Exception {
        questionnaireService.questionnaire(userId, questionnaire);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

}
