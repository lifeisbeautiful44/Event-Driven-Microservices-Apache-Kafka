package com.lib.apacekafka.ws.ProductMicroservice.error;

import java.util.Date;

public class ErrorResponse {
    private Date timestamp;
    private String path;
    private String message;

    public ErrorResponse(Date timestamp, String path, String message) {
        this.timestamp = timestamp;
        this.path = path;
        this.message = message;
    }
}
