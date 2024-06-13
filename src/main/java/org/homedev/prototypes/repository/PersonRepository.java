package org.homedev.prototypes.repository;

import org.homedev.prototypes.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "select id from person order by id desc limit 1", nativeQuery = true)
    Optional<Long> findLastId();

    /*@Query(value = "select * from person where inn like '?1%' limit 50", nativeQuery = true)
    List<Person> findAllByInnStartingWith(String prefix);*/

    @Query(value = "SELECT * FROM person WHERE inn LIKE :prefix || '%' LIMIT 20", nativeQuery = true)
    List<Person> findAllByInnStartingWith(@Param("prefix") String prefix);
}
