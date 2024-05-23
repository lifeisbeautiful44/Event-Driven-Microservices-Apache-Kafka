package com.lib.apacekafka.ws.ProductMicroservice.service.impl;

import com.lib.apacekafka.ws.ProductMicroservice.event.ProductCreatedEvent;
import com.lib.apacekafka.ws.ProductMicroservice.payloads.ProductCreateRequest;
import com.lib.apacekafka.ws.ProductMicroservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
public class ProductServiceImpl implements ProductService {

    KafkaTemplate<String,ProductCreatedEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(ProductCreateRequest productCreateRequest) throws Exception {
        String productId = UUID.randomUUID().toString();
        //Todo: persist in the database and publish the event

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,
                productCreateRequest.getTitle(),
                productCreateRequest.getDescription(),
                productCreateRequest.getPrice(),
                productCreateRequest.getQuantity());
        //For aschyronous jsut comment this:
//      CompletableFuture<SendResult<String,ProductCreatedEvent>> future =  kafkaTemplate.send("product-created-events-topic",productId,productCreatedEvent);
//
//      future.whenComplete((result,exception)->{
//          if(exception !=null)
//          {
//     logger.error("Failed to send message :{} ", exception.getMessage());
//          }else{
//logger.info("Message send successfully :{} ", result.getRecordMetadata());
//          }
//      });
//        logger.info("printed first  ", productId);

//        #For synchronization
        SendResult<String,ProductCreatedEvent> result =  kafkaTemplate.send("product-created-events-topic",productId,productCreatedEvent).get();

        logger.info("Partitions :{}", result.getRecordMetadata().partition());
        logger.info("Topic :{}", result.getRecordMetadata().topic());
        logger.info("Offset :{}", result.getRecordMetadata().offset());

        return productId;
    }
}
