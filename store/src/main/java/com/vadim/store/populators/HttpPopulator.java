package com.vadim.store.populators;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vadim.domain.product.Category;
import com.vadim.store.exception.CommonException;
import com.vadim.store.http.HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpPopulator implements Populator {

    public List<Category> getListOfCategories() {
        HttpURLConnection connection = new HttpClient().getConnection("/categories", "GET");
        String contentType = connection.getHeaderField("Content-type");
        if (contentType.equals("application/json")) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(connection.getInputStream(), new TypeReference<List<Category>>() {
                });
            } catch (IOException e) {
                throw new CommonException("Error to get categories", e);
            }
        } else {
            System.out.println("Unexpected content-type");
        }
        connection.disconnect();
        return null;
    }
}
