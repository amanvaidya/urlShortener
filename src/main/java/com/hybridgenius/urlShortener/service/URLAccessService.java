package com.hybridgenius.urlShortener.service;

import com.hybridgenius.urlShortener.dto.URLShortenerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class URLAccessService {
    HashMap<String, String> urlMap = new HashMap<>();
    HashMap<String, Integer> clickMap = new HashMap<>();
    int size = urlMap.size();
    @Value("${app.url.domain}")
    private String shortUrl;

    public String encodeUrl(URLShortenerDto urlShortenerDto){
        String url = urlShortenerDto.getUrl();
        String alias = urlShortenerDto.getAlias();
        if(alias == null){
            alias = shortUrl+size;
        }
        urlMap.put(alias, url);
        size++;
        return alias;
    }

    public String decodeUrl(String url){
        String actualUrl = urlMap.get(url);
        updateURLClicks(url);
        return actualUrl;
    }

    private void updateURLClicks(String url){
        if(clickMap.containsKey(url)){
            int click = clickMap.get(url);
            clickMap.put(url, click+1);
        }else{
            clickMap.put(url, 1);
        }
    }

}
