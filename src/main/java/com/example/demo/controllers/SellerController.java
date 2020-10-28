package com.example.demo.controllers;

import com.example.demo.entities.Seller;
import com.example.demo.entities.Thing;
import com.example.demo.entities.dto.ServeDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("sellers")
public class SellerController {

    private static final String SELLER_URL = "http://10.102.197.193:8089";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);


    @Autowired
    public SellerController() {
    }

    @GetMapping
    public ResponseEntity<List<Seller>> show() {
        ResponseEntity<Seller[]> response = restTemplate
                    .exchange(SELLER_URL + "/sellers", HttpMethod.GET, headersEntity, Seller[].class);

        List<Seller> sellers = Arrays.asList(Objects.requireNonNull(response.getBody()));
        return ResponseEntity.ok(sellers);
    }

    @GetMapping("{id}")
    public ResponseEntity<Seller> showById(@PathVariable UUID id) {
        ResponseEntity<Seller> response = restTemplate
                .exchange(SELLER_URL + "/sellers/" + id, HttpMethod.GET, headersEntity, Seller.class);
        Seller seller = Objects.requireNonNull(response.getBody());
        return ResponseEntity.ok(seller);
    }

    @PostMapping
    public ResponseEntity<Void> ThingsFromSeller(@RequestBody String deliverJson) {
        HttpEntity<String> things = new HttpEntity<>(deliverJson);
        ResponseEntity<Void> response = restTemplate
                .exchange(SELLER_URL + "/sellers",
                        HttpMethod.POST, things, Void.class);

        if (response.getStatusCodeValue() == 200)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
