package com.test.gcpstorage.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Api(tags = "Home")
@RestController
public class HelloController {
    @ApiOperation(value = "Index", notes = "Index")
    @GetMapping("/")
    public String versionInfo() {
        return "Hello from File Upload, version: 0.0.4";
    }

//    @ApiOperation(value = "list files", notes = "list files")
//    @GetMapping("/files")
//    public String getFileLists()
//    {
//        String PROJECT_ID = "fresh-metrics-328615";
//        String PATH_TO_JSON_KEY = "D:\\SourceCode\\gcp-storage\\lucky-nature-286904-e4cf2bdffe2d.json";
//        String BUCKET_NAME = "my-test-0123459";
//        String OBJECT_NAME = "1.txt";
//
//        String fileContent = "";
//
//        try {
//            StorageOptions options = StorageOptions.newBuilder()
//                    .setProjectId(PROJECT_ID)
//                    .setCredentials(GoogleCredentials.fromStream(
//                            new FileInputStream(PATH_TO_JSON_KEY))).build();
//
//            Storage storage = options.getService();
//            Blob blob = storage.get(BUCKET_NAME, OBJECT_NAME);
//            ReadChannel r = blob.reader();
//
//            fileContent = new String(blob.getContent());
//
//            System.out.println(fileContent);
//        }
//        catch (FileNotFoundException ex)
//        {
//            // insert code to run when exception occurs
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return fileContent;
//    }
}
