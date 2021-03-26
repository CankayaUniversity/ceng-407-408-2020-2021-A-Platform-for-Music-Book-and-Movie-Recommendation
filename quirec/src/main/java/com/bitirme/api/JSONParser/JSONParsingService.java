package com.bitirme.api.JSONParser;

public interface JSONParsingService {

    Object get(String url);

    Object getForObject(String url, String param);

}