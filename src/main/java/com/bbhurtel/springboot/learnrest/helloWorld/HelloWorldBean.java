package com.bbhurtel.springboot.learnrest.helloWorld;

public class HelloWorldBean {

    public HelloWorldBean(String message) {
        this.message = message;
    }

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
