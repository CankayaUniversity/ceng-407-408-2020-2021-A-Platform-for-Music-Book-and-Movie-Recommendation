package com.bitirme.api.Spotify.impl;

import com.bitirme.api.JSONParser.JSONParsingService;
import com.bitirme.api.Spotify.service.SpotifyService;
import com.bitirme.dataset.dao.MusicDao;
import com.bitirme.quirec.questionnarie.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;

public class SpotifyServiceImpl implements SpotifyService {

    @Autowired
    JSONParsingService parsingService;

    @Autowired
    MusicDao musicDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void getMusic(){

    }

    private void getCategories() {

    }
}
