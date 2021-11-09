package com.test.gcpstorage.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.test.gcpstorage.model.StorageFileInfo;
import com.test.gcpstorage.utils.FileTypeChecker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class GoogleStorageClientAdapter2 {
    @Value("${projectid}")
    private String PROJECT_ID;

    @Value("${bucketname}")
    private String BUCKET_NAME;

    Storage storage;


    public StorageFileInfo uploadObject(MultipartFile file) throws IOException {


        StorageFileInfo storageFileInfo = new StorageFileInfo();

        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        String newFileName =  UUID.randomUUID() + "." + fileExtension;
        String contentType = FileTypeChecker.identifyFileContentType(newFileName);

//         The ID of your GCS object
//         String objectName = "your-object-name";
//
//         The path to your file to upload
//         String filePath = "path/to/your/file"

        Storage storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
        BlobId blobId = BlobId.of(BUCKET_NAME, newFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, file.getBytes());

        storageFileInfo.name = newFileName;

        return storageFileInfo;
    }


}
