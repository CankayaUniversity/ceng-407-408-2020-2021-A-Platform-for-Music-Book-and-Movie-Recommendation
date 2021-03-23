package com.bitirme.api.GoogleBooks.impl;

import com.bitirme.dataset.dao.BookDao;
import com.bitirme.dataset.model.Book;
import com.bitirme.api.GoogleBooks.service.GoogleBooksService;
import com.bitirme.api.JSONParser.JSONParsingService;
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
public class GoogleBooksServiceImpl implements GoogleBooksService {

    @Autowired
    JSONParsingService parsingService;

    @Autowired
    BookDao bookDao;

    @Autowired
    CategoryDao categoryDao;

    public void getBooks(){
        String getBooksUrl = "https://www.googleapis.com/books/v1/volumes?q={search type}&maxResults=10&startIndex=30"; //TODO: startIndex generalization
        String searchType = "{printType, books}";
        LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> resource = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>)parsingService.getForObject(getBooksUrl, searchType);

        ArrayList<LinkedHashMap<String, Object>> books = resource.get("items");

        for (LinkedHashMap<String, Object> book : books) {
            String resourceId = (String) book.get("id");
            Book existingBook = bookDao.findBookByResourceId(resourceId);

            if (existingBook == null) {
                LinkedHashMap<String, Object> bookInfo = (LinkedHashMap<String, Object>) book.get("volumeInfo");
                List<String> categories = (List<String>) bookInfo.get("categories");

                if(categories != null){
                    Book newBook = new Book();

                    newBook.setResourceId(resourceId);
                    newBook.setTitle((String) bookInfo.get("title"));

                    LinkedHashMap<String, Object> links = (LinkedHashMap<String, Object>) bookInfo.get("imageLinks");
                    newBook.setCover(links.get("thumbnail").toString());

                    List<String> authors = (List<String>) bookInfo.get("authors");
                    if (authors != null) newBook.setAuthor(authors.get(0));
                    else newBook.setAuthor("Author Unknown");

                    categories
                            .forEach(
                                    category -> {
                                        Categories c = categoryDao.findCategoriesByCategoryTypeAndName(CategoryType.BOOK, category);

                                        if (c == null) {
                                            Categories newCategory = new Categories();

                                            newCategory.setCategoryType(CategoryType.BOOK);
                                            newCategory.setName(category);
                                            categoryDao.saveAndFlush(newCategory);

                                            newBook.getCategories().add(newCategory);
                                        }

                                        else
                                            newBook.getCategories().add(c);
                                    }
                            );

                    bookDao.saveAndFlush(newBook);
                }
            }
        }
    }

    public void getBookDetail(String resourceId){
        String searchBooksUrl = "https://www.googleapis.com/books/v1/volumes/{volumeId}";
        LinkedHashMap<String, ArrayList<Object>> response = (LinkedHashMap<String, ArrayList<Object>>)parsingService.getForObject(searchBooksUrl, resourceId);
    }

}