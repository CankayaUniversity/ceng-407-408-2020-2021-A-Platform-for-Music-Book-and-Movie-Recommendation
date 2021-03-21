package com.bitirme.quirec.api.GoogleBooks.impl;

import com.bitirme.dataset.dao.BookDao;
import com.bitirme.dataset.model.Book;
import com.bitirme.quirec.api.GoogleBooks.service.GoogleBooksService;
import com.bitirme.quirec.api.JSONParser.JSONParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional
public class GoogleBooksServiceImpl implements GoogleBooksService {

    @Autowired
    JSONParsingService parsingService;

    @Autowired
    BookDao bookDao;

    public void getBooks(){
        String getBooksUrl = "https://www.googleapis.com/books/v1/volumes?q={search type}&maxResults=40";
        String searchType = "{printType, books}";
        LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> resource = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>)parsingService.getForObject(getBooksUrl, searchType);

        //TODO: tek gette 40 item geliyor, totalItem sayısına göre for dönülmeli

        ArrayList<LinkedHashMap<String, Object>> books = resource.get("items");

        for (int i=0; i<books.size(); i++){
            String resourceId = (String) books.get(i).get("id");
            Book existingBook = bookDao.findBookByResourceId(resourceId);

            if(existingBook == null){
                LinkedHashMap<String, Object> bookInfo = (LinkedHashMap<String, Object>) books.get(i).get("volumeInfo");
                Book newBook = new Book();

                newBook.setResourceId(resourceId);
                newBook.setTitle((String) bookInfo.get("title"));

                List<String> authors = (List<String>) bookInfo.get("authors");
                if(authors != null) //TODO: null gelenler oluyor, değiştirilebilir
                    newBook.setAuthor(authors.get(0));

                bookDao.saveAndFlush(newBook);
            }
        }
    }

}

//Search: https://www.googleapis.com/books/v1/volumes/WYQ_AQAAIAAJ resourceId