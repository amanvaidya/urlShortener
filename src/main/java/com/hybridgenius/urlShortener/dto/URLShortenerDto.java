package com.hybridgenius.urlShortener.dto;

public class URLShortenerDto {
    private  String url = null;
    private String alias = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
