package com.fmjava.core.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
   private Boolean success;
   private String message;
    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
