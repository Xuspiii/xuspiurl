package org.xuspi.xuspiurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.xuspi.xuspiurl.model.Counter;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {

    @Modifying
    @Query("update Counter c set c.value = c.value + 1")
    void incrementCounter();

    public Counter findById(long id);
}
