package org.opencds.hooks.example.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClient {

    HttpClient httpClient = HttpClient.newHttpClient();

    public HttpResponse<String> invokeService(String url, String cdsHooksRequest) {

            // Create an HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json") // Add the Content-Type header
                    .header("Accept", "application/json") // Optional: Specify the response format
                    .POST(HttpRequest.BodyPublishers.ofString(cdsHooksRequest))               // Specify the HTTP method (GET)
                    .build();

        try {
                // Send the request and receive an HttpResponse
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.body());

                // Print the status code and response body
                System.out.println("Status code: " + response.statusCode());
                System.out.println("Response body: " + response.body());

                return response;
            } catch(IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
