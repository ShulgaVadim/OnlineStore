package com.vadim.store.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vadim.domain.product.Category;
import com.vadim.store.exception.CommonException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;

public class HttpClient {
    HttpURLConnection connection;

    public HttpURLConnection getConnection(String file, String method) {
        try {
            URL address = new URL("http", "localhost", 8001, file);
            String encoding = Base64.getEncoder().encodeToString(("test:test").getBytes("UTF-8"));
            connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            return connection;
        } catch (IOException e) {
            throw new CommonException("Error to get connection", e);
        }
    }


    public void addToCart(List<Category> categories, int categoryId, int productId) {
        connection = getConnection("/cart", "POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);
        try {
            OutputStream os = connection.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            byte[] postData = mapper.writeValueAsBytes(categories.get(categoryId).getProducts().get(productId));
            os.write(postData);
        } catch (IOException e) {
            throw new CommonException("Error to add to cart", e);
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        } catch (IOException e) {
            throw new CommonException("Error to generate response", e);
        } finally {
            connection.disconnect();
        }
    }
}
