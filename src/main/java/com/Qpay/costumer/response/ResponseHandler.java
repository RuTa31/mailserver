package com.Qpay.costumer.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(String pMessage, HttpStatus pHttpStatus,
            Object pResponseObject) {

        Map<String, Object> response = new HashMap();
        response.put("message", pMessage);
        response.put("httpStatus", pHttpStatus);
        response.put("data", pResponseObject);

        return new ResponseEntity<>(response, pHttpStatus);
    }
}
