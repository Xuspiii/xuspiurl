package org.xuspi.xuspiurl.service;

import org.springframework.stereotype.Service;
import org.xuspi.xuspiurl.model.Url;

@Service
public interface UrlService {

    public Url generateShortUrl(String originalUrl);
    public Url getOriginalUrl(String shortUrl);
}
