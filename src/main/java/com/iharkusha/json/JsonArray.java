package com.iharkusha.json;

import java.util.ArrayList;
import java.util.List;

public class JsonArray implements JsonElement {
    private List<JsonElement> elements;

    public JsonArray() {
        elements = new ArrayList<>();
    }

    public void add(JsonElement element) {
        elements.add(element);
    }

    @Override
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(elements.get(i).toJsonString());
        }
        sb.append("]");
        return sb.toString();
    }
}
