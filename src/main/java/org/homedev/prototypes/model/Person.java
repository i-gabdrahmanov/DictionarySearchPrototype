package org.homedev.prototypes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "person")
@EqualsAndHashCode(of = {"inn"})
public class Person {
    @Id
    private Long id;

    private String inn;

    private String fio;

}