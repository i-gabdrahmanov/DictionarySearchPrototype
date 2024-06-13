package org.homedev.prototypes.repository;

import org.homedev.prototypes.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "select id from person order by id desc limit 1", nativeQuery = true)
    Optional<Long> findLastId();
}
