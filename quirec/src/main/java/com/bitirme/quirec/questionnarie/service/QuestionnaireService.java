package com.bitirme.quirec.questionnarie.service;

import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.model.Questionnaire;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public interface QuestionnaireService {

    List<Categories> getCategories();

    Set<Categories> get(long userId);

    void questionnaire(long userId, List<Questionnaire> questionnaire) throws Exception;

}
