package com.bitirme.dataset.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.dataset.model.Music;

public interface MusicDao extends BaseDao<Music> {

    Music findMusicByResourceId(int resourceId);

}
