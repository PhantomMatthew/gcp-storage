package com.hsbc.gcpstorage.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello from File Upload";
    }

    @GetMapping("/files")
    public String getFileLists()
    {
        String PROJECT_ID = "fresh-metrics-328615";
        String PATH_TO_JSON_KEY = "./fresh-metrics-328615-513773582d73.json";
        String BUCKET_NAME = "spring-bucket-*";
        String OBJECT_NAME = "1.txt";

        String fileContent = "";

        try {
            StorageOptions options = StorageOptions.newBuilder()
                    .setProjectId(PROJECT_ID)
                    .setCredentials(GoogleCredentials.fromStream(
                            new FileInputStream(PATH_TO_JSON_KEY))).build();

            Storage storage = options.getService();
            Blob blob = storage.get(BUCKET_NAME, OBJECT_NAME);
            ReadChannel r = blob.reader();

            fileContent = new String(blob.getContent());

            System.out.println(fileContent);
        }
        catch (FileNotFoundException ex)
        {
            // insert code to run when exception occurs
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fileContent;
    }
}
