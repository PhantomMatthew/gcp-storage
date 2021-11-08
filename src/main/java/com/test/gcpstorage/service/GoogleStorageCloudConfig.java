package com.test.gcpstorage.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.services.storage.Storage;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
//import com.test.gcpstorage.service.GoogleHttpRequestInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@Configuration
public class GoogleStorageCloudConfig {
    @Autowired
    private Environment env;

//    @Value("${bucket}")
//    String BUCKET_NAME;

    @Bean
    Storage configStorageClient() throws GeneralSecurityException, IOException {

//                new Storage(GoogleNetHttpTransport.newTrustedTransport(),
//                new GsonFactory(), new GoogleHttpRequestInitializer());

//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
//                .setProjectId(BUCKET_NAME).build().getService();
        Storage storage = StorageOptions.getDefaultInstance().getService();
        log.info("google_application_credentials " + env.toString());
        return storage;
    }
}