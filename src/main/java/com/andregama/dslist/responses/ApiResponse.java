package com.andregama.dslist.responses;

public class ApiResponse<T> {

    private int status; // 200, 201, 400, etc
    private String message;
    private T data;
    private String messageDeveloper;

    public ApiResponse() {
    }

    public ApiResponse(int status, String message, T data, String messageDeveloper) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.messageDeveloper = messageDeveloper;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessageDeveloper() {
        return messageDeveloper;
    }

    public void setMessageDeveloper(String messageDeveloper) {
        this.messageDeveloper = messageDeveloper;
    }

}