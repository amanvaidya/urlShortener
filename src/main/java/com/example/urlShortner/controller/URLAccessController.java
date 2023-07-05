package com.example.urlShortner.controller;

import com.example.urlShortner.dto.URLShortnerDto;
import com.example.urlShortner.service.URLAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class URLAccessController {
    @Autowired
    private URLAccessService urlAccessService;

    @PostMapping("/encode")
    public String encodeUrl(@RequestBody URLShortnerDto urlShortnerDto){
       return urlAccessService.encodeUrl(urlShortnerDto);
    }

    @GetMapping("{url}")
    public void redirectUrl(@PathVariable String url) throws IOException, URISyntaxException {
        urlAccessService.redirectUrl(url);
    }
}
