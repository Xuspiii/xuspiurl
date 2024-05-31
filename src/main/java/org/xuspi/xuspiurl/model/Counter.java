package org.xuspi.xuspiurl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Counter {

    @Id
    private long id = 1L;

    @Column(name = "counter_value")
    private long value;
}
