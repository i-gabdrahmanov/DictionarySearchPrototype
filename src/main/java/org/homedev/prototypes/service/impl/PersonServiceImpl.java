package org.homedev.prototypes.service.impl;

import lombok.RequiredArgsConstructor;
import org.homedev.prototypes.dao.PersonDao;
import org.homedev.prototypes.dto.PersonInitDto;
import org.homedev.prototypes.dto.PersonListDto;
import org.homedev.prototypes.repository.PersonRepository;
import org.homedev.prototypes.service.PersonService;
import org.homedev.prototypes.utils.InnGenerator;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService  {
    private final PersonRepository personRepository;
    private final PersonDao dao;
    @Override
    public void fillDatabase(int count) throws SQLException {
        long id = personRepository.findLastId().orElse(0L) + 1L;
        Set<PersonInitDto> personInitDtos = new HashSet<>();
        while (personInitDtos.size() < count) {
            PersonInitDto p = new PersonInitDto();
            p.setId(id++);
            p.setFio("B D F");
            p.setInn(InnGenerator.generate16RegionInn());
            personInitDtos.add(p);

        }
        dao.saveAll(new ArrayList<>(personInitDtos));
    }

    @Override
    public PersonListDto getPersonByInnPrefix(String innPrefix) {
        return new PersonListDto(personRepository.findAllByInnStartingWith(innPrefix).stream()
                .map(p -> new PersonInitDto(p.getId(), p.getFio(), p.getInn())).toList());
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
