package com.bitirme.quirec.recommendation.model;

import com.bitirme.quirec.questionnarie.model.CategoryType;
import lombok.Data;

@Data
public class Rate {

    private CategoryType type;

    private long itemId;

    private double rate;

}
