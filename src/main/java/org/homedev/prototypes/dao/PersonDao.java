package org.homedev.prototypes.dao;

import org.homedev.prototypes.dto.PersonInitDto;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao {
    void saveAll(List<PersonInitDto> list) throws SQLException;
}
