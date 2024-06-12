package org.homedev.prototypes.repository;

import jakarta.persistence.Entity;
import lombok.NonNull;
import org.hibernate.annotations.BatchSize;
import org.homedev.prototypes.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    @BatchSize(size = 50000)
    <S extends Person> List<S> saveAll(@NonNull Iterable<S> entities);
}
