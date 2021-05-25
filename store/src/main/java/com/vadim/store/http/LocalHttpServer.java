package com.vadim.store.http;

import com.sun.net.httpserver.*;
import com.vadim.store.exception.CommonException;
import com.vadim.store.http.handlers.CategoriesHandler;
import com.vadim.store.http.handlers.ProductCartHandler;

import java.net.InetSocketAddress;

public class LocalHttpServer {
    HttpServer server;

    public void startServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(8001), 0);
            createContext("/categories", new CategoriesHandler());
            createContext("/cart", new ProductCartHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("Server started");
        } catch (Exception ex) {
            throw new CommonException("Error to build server", ex);
        }
    }

    private void createContext(String path, HttpHandler handler) {
        server.createContext(path, handler).setAuthenticator(new BasicAuthenticator("test") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals("test") && pwd.equals("test");
            }
        });
    }
}