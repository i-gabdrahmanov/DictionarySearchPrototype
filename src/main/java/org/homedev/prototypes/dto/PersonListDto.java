package org.homedev.prototypes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonListDto {
    List<PersonInitDto> list;
}
