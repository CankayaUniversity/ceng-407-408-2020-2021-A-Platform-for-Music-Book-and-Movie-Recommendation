package com.bitirme.api.JSONParser;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JSONParsingServiceImpl implements JSONParsingService {

    @Override
    public Object get(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class);
    }

    @Override
    public Object getForPage(String url, int page) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class, page);
    }

    @Override
    public Object getForObject(String url, String param, long index) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class, param, index);
    }

}
