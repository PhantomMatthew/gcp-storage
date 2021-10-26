package com.hsbc.gcpstorage.adapter;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.StorageObject;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;

@Component
public class GoogleStorageClientAdapter {
    Storage storage;
    String bucketName;

    public GoogleStorageClientAdapter(@Autowired Storage storage, @Value("${bucket}") String bucketName) {
        this.storage = storage;
        this.bucketName = bucketName;
    }

    public Boolean upload(MultipartFile file, String prefixName) throws IOException {
        StorageObject object = new StorageObject();
        object.setName(file.getOriginalFilename());
        InputStream targetStream = new ByteArrayInputStream(file.getBytes());
        storage.objects().insert(bucketName, object, new AbstractInputStreamContent(file.getOriginalFilename()) {
            @Override
            public long getLength() throws IOException {
                return file.getSize();
            }

            @Override
            public boolean retrySupported() {
                return false;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return targetStream;
            }
        }).execute();
        return true;
    }


    public StorageObject download(String fileName) throws IOException {
        StorageObject object = storage.objects().get(bucketName, fileName).execute();
        File file = new File("./" + fileName);
        FileOutputStream os = new FileOutputStream(file);

        storage.getRequestFactory()
                .buildGetRequest(new GenericUrl(object.getMediaLink()))
                .execute()
                .download(os);
        object.set("file", file);
        return object;
    }

//    public static void downloadObjects(String projectid, String bucketName, String objectName) {
//        Storage storage = StorageOptions.newBuilder().setProjectId().build().getService();
//
//        Blob blob = storage.get.get(BlobId.of(bucketName, objectName));
//        blob.downloadTo(Paths.get("./" + objectName));
//    }
}

