package com.iharkusha.json;

public class JsonString implements JsonElement {
    private String key;
    private String value;

    public JsonString(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toJsonString() {
        return "\"" + key + "\":\"" + value + "\"";
    }
}
