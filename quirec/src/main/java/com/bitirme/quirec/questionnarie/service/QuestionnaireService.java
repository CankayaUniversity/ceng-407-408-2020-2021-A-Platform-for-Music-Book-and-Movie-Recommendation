package com.bitirme.quirec.questionnarie.service;

import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.model.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    List<Categories> getCategories();

    void questionnaire(long userId, List<Questionnaire> questionnaire) throws Exception;

}
