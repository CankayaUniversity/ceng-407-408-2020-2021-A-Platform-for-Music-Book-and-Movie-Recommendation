package com.bitirme.api.JSONParser;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JSONParsingServiceImpl implements JSONParsingService {

    @Override
    public Object getForObject(String url, String param) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class, param);
    }

}