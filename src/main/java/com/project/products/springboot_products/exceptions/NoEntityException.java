package com.project.products.springboot_products.exceptions;

public class NoEntityException extends RuntimeException {
    private String message;


    public NoEntityException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
