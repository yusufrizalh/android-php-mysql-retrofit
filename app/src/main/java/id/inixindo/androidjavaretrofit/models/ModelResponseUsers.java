package id.inixindo.androidjavaretrofit.models;

import java.util.List;

public class ModelResponseUsers {
    int code;
    String message;
    List<ModelUsers> data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<ModelUsers> getData() {
        return data;
    }
}
