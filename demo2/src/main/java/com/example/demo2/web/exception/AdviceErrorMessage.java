package com.example.demo2.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdviceErrorMessage {

    private String errorMessage;
    public AdviceErrorMessage(Throwable e) {
        this.errorMessage = e.getMessage();
    }
}
