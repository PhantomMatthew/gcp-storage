package com.test.gcpstorage.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.util.Arrays;

public class GoogleHttpRequestInitializer implements HttpRequestInitializer {
    GoogleCredentials credentials;
    HttpCredentialsAdapter adapter;

//    @Value("${path_to_json_key}")
//    String PATH_TO_JSON_KEY;

    @Override
    public void initialize(HttpRequest httpRequest) throws IOException {
        credentials = GoogleCredentials
                .getApplicationDefault()
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));

//        credentials = GoogleCredentials.fromStream(new FileInputStream(PATH_TO_JSON_KEY));
        adapter = new HttpCredentialsAdapter(credentials);

        adapter.initialize(httpRequest);
        httpRequest.setConnectTimeout(60000); // 1 minute connect timeout
        httpRequest.setReadTimeout(60000);

    }

    public GoogleCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(GoogleCredentials credentials) {
        this.credentials = credentials;
    }

    public HttpCredentialsAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(HttpCredentialsAdapter adapter) {
        this.adapter = adapter;
    }
}