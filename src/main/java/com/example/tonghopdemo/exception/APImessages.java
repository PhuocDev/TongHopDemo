package com.example.tonghopdemo.exception;

import lombok.Data;

@Data
public class APImessages {
    private String message;

    public APImessages(String message) {
        this.message = message;
    }

    public APImessages() {
    }

}
