package com.lib.apacekafka.ws.ProductMicroservice.controller;


import com.lib.apacekafka.ws.ProductMicroservice.error.ErrorResponse;
import com.lib.apacekafka.ws.ProductMicroservice.payloads.ProductCreateRequest;
import com.lib.apacekafka.ws.ProductMicroservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody ProductCreateRequest productCreateRequest)
    {
        String productId = null;
        try {
            productId = productService.createProduct(productCreateRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(new Date(),"/product/create",e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
