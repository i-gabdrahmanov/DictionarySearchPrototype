package org.homedev.prototypes.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"inn"})
public class PersonInitDto {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String inn;

    private String fio;

}