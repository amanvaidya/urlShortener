package com.example.urlShortner.service;

import com.example.urlShortner.dto.URLShortnerDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class URLAccessService {
    HashMap<String, String> map = new HashMap<>();
    int size = map.size();
    public String encodeUrl(URLShortnerDto urlShortnerDto){
        String url = urlShortnerDto.getUrl();
        String alias = urlShortnerDto.getAlias();
        if(alias == null){
            alias = "myUrl"+size;
        }
        map.put(alias, url);
        size++;
        return alias;
    }

    public String decodeUrl(String url){
        String actualUrl = map.get(url);
        return actualUrl;
    }

    public void redirectUrl(String url) throws IOException {
        String actualUrl = decodeUrl(url);
        System.out.println("actualUrl: "+actualUrl);
        URL webUrl = new URL(actualUrl);
        webUrl.openConnection();
    }
}
