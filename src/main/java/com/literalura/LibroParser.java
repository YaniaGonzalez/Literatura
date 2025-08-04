package com.literalura;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class LibroParser {
    public List<Libro> parsearRespuesta(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray docs = jsonObject.getAsJsonArray("docs");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Libro>>() {}.getType();
        return gson.fromJson(docs, listType);
    }
}
