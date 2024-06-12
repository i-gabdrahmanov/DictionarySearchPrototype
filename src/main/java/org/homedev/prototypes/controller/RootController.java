package org.homedev.prototypes.controller;

import lombok.RequiredArgsConstructor;
import org.homedev.prototypes.service.PersonService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RootController {

private final PersonService service;
    @GetMapping("/fillDatabase")
    public ResponseEntity<Void> fillDatabase (@RequestParam int count) {
        service.fillDatabase(count);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
