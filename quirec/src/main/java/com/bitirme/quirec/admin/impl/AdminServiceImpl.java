package com.bitirme.quirec.admin.impl;

import com.bitirme.quirec.admin.service.AdminService;
import com.bitirme.quirec.recommendation.dao.RatingDao;
import com.bitirme.quirec.recommendation.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    RatingDao ratingDao;

    @Override
    public List<Rating> getRatings() {
        return ratingDao.findAll();
    }

}
