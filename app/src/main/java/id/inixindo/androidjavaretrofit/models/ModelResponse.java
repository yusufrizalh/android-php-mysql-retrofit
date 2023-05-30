package id.inixindo.androidjavaretrofit.models;

import java.util.List;

public class ModelResponse {
    int code;
    String message;
    List<ModelRiders> data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<ModelRiders> getData() {
        return data;
    }

}
