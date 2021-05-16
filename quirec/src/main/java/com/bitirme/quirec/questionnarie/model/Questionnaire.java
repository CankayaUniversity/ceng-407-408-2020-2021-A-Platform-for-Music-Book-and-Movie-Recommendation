package com.bitirme.quirec.questionnarie.model;

import lombok.Data;

@Data
public class Questionnaire {

    CategoryType categoryType;

    Long originalItemId;

    Double userRating;

}
