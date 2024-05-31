package org.xuspi.xuspiurl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Lob;
import lombok.Data;
import jakarta.persistence.Id;

@Data
@Entity
public class Url {

    @Id
    private long id;

    @Lob
    private String originalUrl;
    private String shortUrl;

    public Url() {

    }

    public Url(Long id, String originalUrl, String shortUrl) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }
}
