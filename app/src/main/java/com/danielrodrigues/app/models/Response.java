package com.danielrodrigues.app.models;

import aj.org.objectweb.asm.Type;

public class Response<T> {
    private String message;
    private Integer code;
    private T data;

    public Response(String message, Integer code) {
        setMessage(message);
        setCode(code);
    }

    public Response(String message, Integer code, T data) {
        setMessage(message);
        setCode(code);
        setData(data);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
