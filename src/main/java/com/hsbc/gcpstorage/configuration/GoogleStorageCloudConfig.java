package com.hsbc.gcpstorage.configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.storage.Storage;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;
import com.hsbc.gcpstorage.adapter.GoogleHttpRequestInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;


@Configuration
public class GoogleStorageCloudConfig {

    @Value("${bucket}")
    String BUCKET_NAME;

    @Bean
    Storage configStorageClient() throws GeneralSecurityException, IOException {


        Storage storage = new Storage(GoogleNetHttpTransport.newTrustedTransport(),
                new GsonFactory(), new GoogleHttpRequestInitializer());
//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
//                .setProjectId(BUCKET_NAME).build().getService();
        return storage;
    }
}