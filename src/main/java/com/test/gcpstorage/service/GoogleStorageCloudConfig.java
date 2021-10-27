package com.test.gcpstorage.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.storage.Storage;
import com.test.gcpstorage.service.GoogleHttpRequestInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;


@Configuration
public class GoogleStorageCloudConfig {

//    @Value("${bucket}")
//    String BUCKET_NAME;

    @Bean
    Storage configStorageClient() throws GeneralSecurityException, IOException {
        Storage storage = new Storage(GoogleNetHttpTransport.newTrustedTransport(),
                new GsonFactory(), new GoogleHttpRequestInitializer());

//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
//                .setProjectId(BUCKET_NAME).build().getService();
        return storage;
    }
}