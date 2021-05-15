package com.bitirme.dataset.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.dataset.model.Book;
import com.bitirme.dataset.model.Music;
import com.bitirme.quirec.questionnarie.model.Categories;

import java.util.List;

public interface BookDao extends BaseDao<Book> {

    Book findBookByResourceId(String resourceId);

    List<Book> findBookByCategories(Categories category);

    List<Book> findAllByTitleLikeIgnoreCase(String title);


}
