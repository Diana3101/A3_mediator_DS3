package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.entities.dto.CreateOrderDTO;
import com.example.demo.entities.dto.OrderThingsDTO;
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
@RequestMapping("orders")
public class OrderController {

    private static final String ORDER_URL = "http://10.106.184.36:8088";
    private static final String CUSTOMERS_URL = "http://10.100.180.19:8086";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Autowired
    public OrderController() {

    }

    @GetMapping
    public ResponseEntity<List<Order>> show(){
        ResponseEntity<Order[]> response = restTemplate
                .exchange(ORDER_URL + "/orders", HttpMethod.GET, headersEntity, Order[].class);
        List<Order> orders = Arrays.asList(Objects.requireNonNull(response.getBody()));
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orderThings")
    public @ResponseBody
    OrderThingsDTO showThings() {
        ResponseEntity<OrderThingsDTO> response = restTemplate
                .exchange(ORDER_URL + "/orders/orderThings",
                        HttpMethod.GET, headersEntity, OrderThingsDTO.class);
        return response.getBody();
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> showById(@PathVariable UUID id) {
        ResponseEntity<Order> response = restTemplate
                .exchange(ORDER_URL + "/orders/" + id, HttpMethod.GET, headersEntity, Order.class);
        Order order = Objects.requireNonNull(response.getBody());
        return ResponseEntity.ok(order);
    }


    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDTO createOrder) {
        createOrder.getCustomer().setCustomerId(UUID.randomUUID());
        //System.out.println(createOrder.getCustomer());

        HttpEntity<CreateOrderDTO> createOrderDTOHttpEntity = new HttpEntity<>(createOrder);
        ResponseEntity<Void> response = restTemplate
                .exchange(ORDER_URL + "/orders",
                        HttpMethod.POST, createOrderDTOHttpEntity, Void.class);

        if (response.getStatusCodeValue() == 200) {
            saveCustomer(createOrder.getCustomer());
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.badRequest().build();
    }

    private void saveCustomer(Customer customer) {
        HttpEntity<Customer> customerHttpEntity = new HttpEntity<>(customer);
        ResponseEntity<Void> response = restTemplate
                .exchange(CUSTOMERS_URL + "/customers",
                        HttpMethod.POST, customerHttpEntity, Void.class);
    }


}
