package com.jcble.jcparking.common.dto.response;


import java.util.List;

public class DevicesRespDto {
    private Boolean error;

    private String message;

    private List<Data> data;

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getError() {
        return this.error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }

}
