package com.lib.apacekafka.ws.ProductMicroservice.event;

import java.math.BigDecimal;

public class ProductCreatedEvent {
    private String id;

    private String title;
    private String description;

    private BigDecimal price;
    private Integer quantity;

    public ProductCreatedEvent(){}
    public ProductCreatedEvent(String id, String title, String description, BigDecimal price, Integer quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
