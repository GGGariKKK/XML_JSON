package json;

import json.JsonElement;
import json.JsonString;

import java.util.ArrayList;
import java.util.List;

public class JsonObject implements JsonElement {
    private List<JsonElement> elements;

    public JsonObject() {
        elements = new ArrayList<>();
    }

    public void add(JsonElement element) {
        elements.add(element);
    }

    @Override
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(elements.get(i).toJsonString());
        }
        sb.append("}");
        return sb.toString();
    }

    public static class Builder {
        private JsonObject jsonObject;

        public Builder() {
            jsonObject = new JsonObject();
        }

        public Builder add(String name, String value) {
            jsonObject.add(new JsonString(name, value));
            return this;
        }

        public Builder add(String name, Number value) {
            jsonObject.add(new JsonString(name, value.toString()));
            return this;
        }

        public Builder add(String name, Boolean value) {
            jsonObject.add(new JsonString(name, value.toString()));
            return this;
        }

        public Builder add(String name, JsonElement value) {
            jsonObject.add(new JsonString(name, value.toJsonString()));
            return this;
        }

        public JsonObject build() {
            return jsonObject;
        }
    }
}
