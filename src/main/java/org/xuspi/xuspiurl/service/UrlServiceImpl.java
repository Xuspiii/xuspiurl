package org.xuspi.xuspiurl.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.xuspi.xuspiurl.model.Counter;
import org.xuspi.xuspiurl.model.Url;
import org.xuspi.xuspiurl.repository.CounterRepository;
import org.xuspi.xuspiurl.repository.UrlRepository;

@Component
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final CounterRepository counterRepository;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, CounterRepository counterRepository) {
        this.urlRepository = urlRepository;
        this.counterRepository = counterRepository;
    }

    @PostConstruct
    public void initCounter() {
        if (!counterRepository.existsById(1L)) {
            Counter counter = new Counter();
            counter.setValue(100000000000L);
            counterRepository.save(counter);
        }
    }

    @Transactional
    @Override
    public Url generateShortUrl(String originalUrl) {
        counterRepository.incrementCounter();
        Counter counter = counterRepository.findById(1L);
        System.out.println("value1: " + counter.getValue());
        String shortUrl = encodeBase62(counter.getValue());
        Url url = new Url(counter.getValue(), originalUrl, shortUrl);
        System.out.println(url.getId());
        urlRepository.save(url);

        return url;
    }

    @Override
    public Url getOriginalUrl(String shortUrl) {
        Long id = decodeBase62(shortUrl);
        return urlRepository.findById(id).orElse(null);
    }

    private static final String Alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String encodeBase62(Long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.insert(0, Alphabet.charAt((int) (value % 62)));
            value /= 62;
        }
        return sb.toString();
    }

    private long decodeBase62(String s) {
        long n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n * 62 + convert(s.charAt(i));
        }
        return n;
    }

    public int convert(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }
}
