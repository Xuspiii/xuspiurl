package org.xuspi.xuspiurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xuspi.xuspiurl.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {

    public Url findByShortUrl(String shortUrl);
}
