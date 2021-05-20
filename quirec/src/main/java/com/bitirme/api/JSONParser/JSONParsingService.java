package com.bitirme.api.JSONParser;

public interface JSONParsingService {

    Object get(String url);

    String getForRecommendation(String url);

    Object getForPage(String url, int page);

    Object getForObject(String url, String param, long index);

}