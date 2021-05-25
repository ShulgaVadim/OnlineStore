package com.vadim.store.http.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.vadim.domain.product.Category;
import com.vadim.store.populators.DataBasePopulator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CategoriesHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        DataBasePopulator dbp = new DataBasePopulator();
        List<Category> categories = dbp.getListOfCategories();
        ObjectMapper mapper = new ObjectMapper();
        byte[] jsonInBytes = mapper.writeValueAsBytes(categories);
        Headers headers = httpExchange.getResponseHeaders();
        headers.add("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(200, jsonInBytes.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(jsonInBytes);
        os.close();
    }
}

