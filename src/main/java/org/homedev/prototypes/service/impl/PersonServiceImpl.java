package org.homedev.prototypes.service.impl;

import lombok.RequiredArgsConstructor;
import org.homedev.prototypes.dao.PersonDao;
import org.homedev.prototypes.dto.PersonInitDto;
import org.homedev.prototypes.service.PersonService;
import org.homedev.prototypes.utils.InnGenerator;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService  {
   // private final PersonRepository personRepository;
    private final PersonDao dao;
    @Override
    public void fillDatabase(int count) throws SQLException {
        Set<PersonInitDto> personInitDtos = new HashSet<>();
        while (personInitDtos.size() < count) {
            PersonInitDto p = new PersonInitDto();
            p.setFio("B D F");
            p.setInn(InnGenerator.generate16RegionInn());
            personInitDtos.add(p);
        }
        dao.saveAll(new ArrayList<>(personInitDtos));
    }

    private void saveAll(Set<PersonInitDto> set) {
        List<List<PersonInitDto>> chunks = chunkList(new ArrayList<>(set), 100_000);
       // chunks.forEach(personRepository::saveAll);
    }
    public static <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        int startIndex = 0;
        while (startIndex < list.size()) {
            int endIndex = Math.min(startIndex + chunkSize, list.size());
            chunks.add(list.subList(startIndex, endIndex));
            startIndex = endIndex;
        }
        return chunks;
    }
}
