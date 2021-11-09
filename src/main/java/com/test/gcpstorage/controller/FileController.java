package com.test.gcpstorage.controller;

import com.test.gcpstorage.model.StorageFileInfo;
import com.test.gcpstorage.response.ResponseHandler;
import com.test.gcpstorage.service.GoogleStorageClientAdapter2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController(value = "/files")
public class FileController {
    @Autowired
    GoogleStorageClientAdapter2 googleStorageClientAdapter;

    @ApiOperation(value = "upload file", notes = "upload file")
    @PostMapping(path = "*/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Object uploadFile(@RequestPart(value = "file", required = true) MultipartFile file)  {
        StorageFileInfo result;
        try {
            result = googleStorageClientAdapter.uploadObject(file);

            if(result != null) {
                return ResponseHandler.generateResponse("Upload Successfully", HttpStatus.OK, result);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandler.generateResponse("Upload Failed", HttpStatus.BAD_REQUEST, null);
    }
}
