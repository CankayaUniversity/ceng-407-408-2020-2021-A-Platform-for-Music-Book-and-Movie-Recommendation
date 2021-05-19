package com.bitirme.quirec.recommendation.model;

import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Movie;
import com.bitirme.dataset.model.Music;
import lombok.Data;

@Data
public class Recommendation {

    private Music[] musicRecommendations = new Music[20];

    private Book[] bookRecommendations = new Book[20];

    private Movie[] movieRecommendations = new Movie[20];

}
