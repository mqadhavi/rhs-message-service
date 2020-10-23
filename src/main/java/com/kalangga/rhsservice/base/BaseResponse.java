package com.kalangga.rhsservice.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class BaseResponse<T> {

    @JsonIgnore
    private int httpCode;
    
    @JsonIgnore
    private Boolean isSuccess =true;

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("status_message")
    private String statusMessage;
    
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("status_message_bahasa")
    private String statusMessageBahasa;

    private T data;

    public void sendNotFound(T aData, String message) {
        this.httpCode = 404;
        this.statusCode = "404";
        this.statusMessage = message == null ? "Data Not Found" :message;
        this.data = aData;
        this.isSuccess = false;
    }

    public void sendValidationError() {
        this.httpCode = 400;
        this.statusCode = "400";
        this.statusMessage = "Request Validation Error";
        this.isSuccess = false;
    }

    public void sendSuccess(T aData, String message) {
        this.httpCode = 200;
        this.statusCode = "200";
        this.statusMessage = message == null ? "Success" :message;;
        this.data = aData;
    }
    
    public void sendCreated(T aData) {
        this.httpCode = 201;
        this.statusCode = "201";
        this.statusMessage = "Data Creation Succeeded";
        this.data = aData;
    }
    
    public void sendBadRequest(T aData, String message) {
        this.httpCode = 400;
        this.statusCode= "400";
        this.statusMessage = message == null ? "Bad Request" :message;
        this.data = aData;
        this.isSuccess = false;
    }

    public void sendBadRequest(T aData,String message, String messageBahasa) {
        this.httpCode = 400;
        this.statusCode= "400";
        this.statusMessage = message == null ? "Bad Request" :message;
        this.statusMessageBahasa = messageBahasa == null ? "Error koneksi" :messageBahasa;
        this.data = aData;
        this.isSuccess = false;
    }
    
    public void sendUnauthorize(T aData,String message) {
        this.httpCode = 401;
        this.statusCode= "401";
        this.statusMessage = message == null ? "Unauthorized" :message;
        this.data = aData;
        this.isSuccess = false;
    }
  
}
