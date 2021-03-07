package com.bitirme.quirec.recommendation.model;

import com.bitirme.base.model.BaseModel;
import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Movie;
import com.bitirme.dataset.model.Music;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "QUIREC", name = "RECOMMENDATION")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Recommendation extends BaseModel {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "MUSIC_ID")
    private Set<Music> musicRecommendations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID")
    private Set<Book> bookRecommendations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MOVIE_ID")
    private Set<Movie> movieRecommendations;

}
