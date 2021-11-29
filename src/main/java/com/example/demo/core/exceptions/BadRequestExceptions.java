package com.example.demo.core.exceptions;

import java.util.HashMap;
import java.util.Map;

public class BadRequestExceptions extends HttpException {

    private final Map<String, String> map = new HashMap<String, String>();

    public BadRequestExceptions() {
        this("Bad Request");
    }

    public BadRequestExceptions(String message) {
        super(400, message);
    }

    public Map<String, String> getExceptions() {
        return this.map;
    }

    public void addException(String key, String message) {
        this.map.put(key, message);
    }
}
