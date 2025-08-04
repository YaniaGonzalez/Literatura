package com.literalura;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LibroAPI {
    private final OkHttpClient client = new OkHttpClient();

    public String buscarLibroPorTitulo(String titulo) throws IOException {
        String url = "https://openlibrary.org/search.json?title=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
