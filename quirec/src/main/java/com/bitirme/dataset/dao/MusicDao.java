package com.bitirme.dataset.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.questionnarie.model.Categories;

import java.util.List;

public interface MusicDao extends BaseDao<Music> {

    Music findMusicByResourceId(int resourceId);

    List<Music> findMusicByCategories(Categories category);

    List<Music> findAllByTitleLike(String title);

}
