package com.vinisnzy.CRUD.infra;

public record ExceptionDTO(String message) {
    public ExceptionDTO {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
    }
}
