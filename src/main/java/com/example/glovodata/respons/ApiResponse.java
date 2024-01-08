package com.example.glovodata.respons;

import lombok.Data;

@Data
public class ApiResponse<D> {
    private boolean success;
    private D data;
    private String message;

}
