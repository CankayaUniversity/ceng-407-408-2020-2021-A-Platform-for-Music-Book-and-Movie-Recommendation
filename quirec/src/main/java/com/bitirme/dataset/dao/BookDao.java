package com.bitirme.dataset.dao;

import com.bitirme.base.dao.BaseDao;
import com.bitirme.dataset.model.Book;

public interface BookDao extends BaseDao<Book> {

    Book findBookByResourceId(String resourceId);

}
