package com.bitirme.quirec.questionnarie.impl;

import com.bitirme.quirec.questionnarie.dao.CategoryDao;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.service.QuestionnaireService;
import com.bitirme.quirec.user.dao.UserDao;
import com.bitirme.quirec.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private CategoryDao categoryDao;

    private UserDao userDao;

    @Override
    public List<Categories> getCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Set<Categories> get(long userId) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        return user.getCategories();
    }

    @Override
    public Set<Categories> create(long userId, List<Categories> questionnaire) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );

        if(user.getCategories() == null){
            questionnaire.stream()
                    .filter(Objects::nonNull)
                    .forEach(
                            categoryParam -> {
                                Categories category = categoryDao.findById(categoryParam.getId()).orElseThrow(
                                        () -> new EntityNotFoundException("category")
                                );
                                user.getCategories().add(category);
                            }
                    );
            return user.getCategories(); //TODO: save and flush eklenecek
        }

        return null; //userın zaten anketi vardır, create değil update çalıştırılmalı
    }

    @Override
    public Set<Categories> update(long userId, List<Categories> questionnaire) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user")
        );
        //TODO: CopyFrom yaz
        return user.getCategories(); //TODO: save and flush eklenecek
    }
}
