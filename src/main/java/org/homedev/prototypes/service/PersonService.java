package org.homedev.prototypes.service;

import org.homedev.prototypes.dto.PersonInitDto;
import org.homedev.prototypes.dto.PersonListDto;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {
    void fillDatabase (int count) throws SQLException;
    PersonListDto getPersonByInnPrefix(String innPrefix);
}
