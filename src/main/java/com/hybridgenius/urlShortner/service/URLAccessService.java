package com.hybridgenius.urlShortner.service;

import com.hybridgenius.urlShortner.dto.URLShortnerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashMap;

@Service
public class URLAccessService {
    HashMap<String, String> map = new HashMap<>();
    int size = map.size();
    @Value("${app.url.domain}")
    private String shortUrl;

    public String encodeUrl(URLShortnerDto urlShortnerDto){
        String url = urlShortnerDto.getUrl();
        String alias = urlShortnerDto.getAlias();
        if(alias == null){
            alias = shortUrl+size;
        }
        map.put(alias, url);
        size++;
        return alias;
    }

    public String decodeUrl(String url){
        String actualUrl = map.get(url);
        return actualUrl;
    }

    public String redirectUrl(String url) throws IOException {
        String actualUrl = decodeUrl(url);
        return actualUrl;
    }
}
