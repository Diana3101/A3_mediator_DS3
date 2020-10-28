package com.example.demo.controllers;

import com.example.demo.entities.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("things")
public class ThingController {
    private static final String THING_URL = "http://10.102.197.193:8089";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);


    @Autowired
    public ThingController() {
    }

    @GetMapping
    public ResponseEntity<List<Thing>> show() {
        ResponseEntity<Thing[]> response = restTemplate
                .exchange(THING_URL + "/things", HttpMethod.GET, headersEntity, Thing[].class);
        List<Thing> items = Arrays.asList(Objects.requireNonNull(response.getBody()));
        return ResponseEntity.ok(items);
    }

    @GetMapping("{id}")
    public ResponseEntity<Thing> showById(@PathVariable UUID id) {
        ResponseEntity<Thing> response = restTemplate
                .exchange(THING_URL + "/things/" + id, HttpMethod.GET, headersEntity, Thing.class);
        Thing thing = Objects.requireNonNull(response.getBody());
        return ResponseEntity.ok(thing);
    }

}
