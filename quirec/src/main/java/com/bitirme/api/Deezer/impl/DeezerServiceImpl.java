package com.bitirme.api.Deezer.impl;

import com.bitirme.api.JSONParser.JSONParsingService;
import com.bitirme.api.Deezer.service.DeezerService;
import com.bitirme.dataset.dao.MusicDao;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.questionnarie.dao.CategoryDao;
import com.bitirme.quirec.questionnarie.model.Categories;
import com.bitirme.quirec.questionnarie.model.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional
public class DeezerServiceImpl implements DeezerService {

    @Autowired
    JSONParsingService parsingService;

    @Autowired
    MusicDao musicDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void getMusic(){
        List<Categories> categories = categoryDao.findCategoriesByCategoryType(CategoryType.MUSIC);

        categories.forEach(
                category -> {
                    String getMoviesUrl = "https://api.deezer.com/chart/" + category.getOriginalId() + "/tracks";

                    LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> resource = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>) parsingService.get(getMoviesUrl);

                    ArrayList<LinkedHashMap<String, Object>> music = resource.get("data");

                    for (LinkedHashMap<String, Object> musicData : music) {
                        int resourceId = (int) musicData.get("id");
                        Music existingMusic = musicDao.findMusicByResourceId(resourceId);

                        if (existingMusic == null) {
                            Music newMusic = new Music();

                            newMusic.setResourceId(resourceId);
                            newMusic.setTitle((String) musicData.get("title"));

                            LinkedHashMap<String, Object> artist = (LinkedHashMap<String, Object>) musicData.get("artist");
                            newMusic.setArtist((String) artist.get("name"));

                            LinkedHashMap<String, Object> album = (LinkedHashMap<String, Object>) musicData.get("album");
                            newMusic.setCover((String) album.get("cover_medium"));

                            newMusic.getCategories().add(category);

                            musicDao.saveAndFlush(newMusic);
                        }
                    }
                }
        );
    }

    private void getOriginalCategories() {
        String getCategoriesUrl = "https://api.deezer.com/genre";
        LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> resource = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>) parsingService.get(getCategoriesUrl);

        ArrayList<LinkedHashMap<String, Object>> categories = resource.get("data");

        categories
                .forEach(
                        category -> {
                            int id = Integer.parseInt(String.valueOf(category.get("id")));
                            Categories originalC = categoryDao.findCategoriesByCategoryTypeAndOriginalId(CategoryType.MUSIC, id);

                            if(originalC == null) {
                                Categories c = new Categories();
                                c.setCategoryType(CategoryType.MUSIC);
                                c.setName(category.get("name").toString());
                                c.setOriginalId(id);

                                categoryDao.saveAndFlush(c);
                            }
                        }
                );
    }
}
