package org.homedev.prototypes.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.homedev.prototypes.dto.PersonInitDto;
import org.homedev.prototypes.dto.PersonListDto;
import org.homedev.prototypes.service.PersonService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RootController {

private final PersonService service;
    @GetMapping("/fillDatabase")
    @SneakyThrows
    public ResponseEntity<Void> fillDatabase (@RequestParam int count) {
        service.fillDatabase(count);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/getPersonListByInn")
    @SneakyThrows
    public ResponseEntity<PersonListDto> getPersonListByInn (@RequestParam String innPrefix) {
       PersonListDto response = service.getPersonByInnPrefix(innPrefix);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }
}
