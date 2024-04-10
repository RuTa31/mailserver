package com.Qpay.costumer.model;

public class EmailRequest {
    private String to;
    private String name;
    private String body;

    public EmailRequest(String to, String name, String body) {
        this.to = to;
        this.name = name;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
