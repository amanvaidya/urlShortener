package com.hybridgenius.urlShortner.controller;

import com.google.zxing.WriterException;
import com.hybridgenius.urlShortner.dto.URLShortnerDto;
import com.hybridgenius.urlShortner.service.QRCodeGenerator;
import com.hybridgenius.urlShortner.service.URLAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
public class URLAccessController {
    @Autowired
    private URLAccessService urlAccessService;

    @Autowired
    private QRCodeGenerator qRCodeGenerator;

    @PostMapping("/encode")
    public String encodeUrl(@RequestBody URLShortnerDto urlShortnerDto) {
        return urlAccessService.encodeUrl(urlShortnerDto);
    }

    @GetMapping("{url}")
    public ModelAndView redirectUrl(@PathVariable String url) {
        String originalUrl = urlAccessService.decodeUrl(url);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(originalUrl);
        return new ModelAndView(redirectView);
    }

    @GetMapping(value = "qrCode/{shortenedUrl}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable String shortenedUrl) throws IOException, WriterException {
        byte[] qrCodeBytes = qRCodeGenerator.generateQRCodeBytes(shortenedUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentDispositionFormData("attachment", "qrcode.png");

        return new ResponseEntity<>(qrCodeBytes, headers, HttpStatus.OK);
    }
}
