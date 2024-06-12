package org.homedev.prototypes.service.impl;

import lombok.RequiredArgsConstructor;
import org.homedev.prototypes.model.Person;
import org.homedev.prototypes.repository.PersonRepository;
import org.homedev.prototypes.service.PersonService;
import org.homedev.prototypes.utils.InnGenerator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService  {
    private final PersonRepository personRepository;
    @Override
    public void fillDatabase(int count) {
        Set<Person> persons = new HashSet<>();
        while (persons.size() < count) {
            Person p = new Person();
            p.setFio("B D F");
            p.setInn(InnGenerator.generate16RegionInn());
            persons.add(p);
        }
        personRepository.saveAll(persons);
    }

    private void saveAll(Set<Person> set) {
        List<List<Person>> chunks = chunkList(new ArrayList<>(set), 100_000);
        chunks.forEach(personRepository::saveAll);
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
