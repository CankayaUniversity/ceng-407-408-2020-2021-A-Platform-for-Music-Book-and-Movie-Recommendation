package com.bitirme.quirec.questionnarie.service;

import com.bitirme.quirec.questionnarie.model.Categories;

import java.util.List;
import java.util.Set;

public interface QuestionnaireService {

    List<Categories> getCategories();

    Set<Categories> get(long userId);

    Set<Categories> create(long userId, List<Categories> questionnaire);

    Set<Categories> update(long userId, List<Categories> questionnaire);

}
