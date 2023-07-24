package com.hybridgenius.urlShortener.controller;

import com.google.zxing.WriterException;
import com.hybridgenius.urlShortener.dto.URLShortenerDto;
import com.hybridgenius.urlShortener.service.QRCodeGenerator;
import com.hybridgenius.urlShortener.service.URLAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class URLAccessController {
    @Autowired
    private URLAccessService urlAccessService;

    @Autowired
    private QRCodeGenerator qRCodeGenerator;

    @PostMapping("/encode")
    public String encodeUrl(@RequestBody URLShortenerDto urlShortenerDto) {
        return urlAccessService.encodeUrl(urlShortenerDto);
    }

    @GetMapping("{url}")
    public void redirectUrl(@PathVariable String url, HttpServletResponse response) {
        String originalUrl = urlAccessService.decodeUrl(url);
        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", originalUrl);
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
