package org.homedev.prototypes.service;

import java.sql.SQLException;

public interface PersonService {
    void fillDatabase (int count) throws SQLException;
}
