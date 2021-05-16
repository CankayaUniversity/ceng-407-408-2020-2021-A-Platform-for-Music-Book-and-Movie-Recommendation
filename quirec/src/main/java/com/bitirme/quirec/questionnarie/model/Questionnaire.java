package com.bitirme.quirec.questionnarie.model;

import lombok.Data;

@Data
public class Questionnaire {

    private Long categoryId;

    private Long itemId;

    private Double userRating;

}
