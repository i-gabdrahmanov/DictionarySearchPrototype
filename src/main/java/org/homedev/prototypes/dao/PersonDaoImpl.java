package org.homedev.prototypes.dao;

import lombok.RequiredArgsConstructor;
import org.homedev.prototypes.dto.PersonInitDto;
import org.homedev.prototypes.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {
    //private final DaoConfig config;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    private final PersonRepository personRepository;


    @Override
    public void saveAll(List<PersonInitDto> list) throws SQLException {
        // Устанавливаем соединение с базой данных
        Connection conn = DriverManager.getConnection(url, user, password);

        // Создаем подготовленный запрос вставки
        String insertQuery = "INSERT INTO person (id, fio, inn) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(insertQuery);

        // Создаем список значений для вставки
        Iterator<PersonInitDto> iterator = list.listIterator();
        while (iterator.hasNext()) {
            PersonInitDto p = iterator.next();
            statement.setLong(1, p.getId());
            statement.setString(2, p.getFio());
            statement.setString(3, p.getInn());

            // Добавляем запрос в пакет
            statement.addBatch();

        }
        statement.executeBatch();

        // Сохраняем изменения
        conn.commit();

        // Закрываем подготовленный запрос и соединение
        statement.close();
        conn.close();
    }

    // Выполняем пакетный запрос

}
