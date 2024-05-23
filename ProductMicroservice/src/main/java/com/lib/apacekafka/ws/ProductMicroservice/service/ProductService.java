package com.lib.apacekafka.ws.ProductMicroservice.service;

import com.lib.apacekafka.ws.ProductMicroservice.payloads.ProductCreateRequest;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct(ProductCreateRequest productCreateRequest) throws Exception;
}
