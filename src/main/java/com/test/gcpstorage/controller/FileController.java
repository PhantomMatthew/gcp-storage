package com.test.gcpstorage.controller;

import com.google.api.services.storage.model.StorageObject;
import com.google.common.io.Files;
import com.test.gcpstorage.response.ResponseHandler;
import com.test.gcpstorage.service.GoogleStorageClientAdapter;
import com.test.gcpstorage.model.StorageFileInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Api(tags = "New File Management")
@RestController(value = "/newfiles")
public class FileController {
    @Autowired
    GoogleStorageClientAdapter googleStorageClientAdapter;

    @ApiOperation(value = "upload file", notes = "upload file")
    @PostMapping(path = "*/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Object uploadFile(@RequestPart(value = "file", required = true) MultipartFile files)  {
        StorageFileInfo result = null;
        try {
            result = googleStorageClientAdapter.upload(files, "prefix");

            if(result != null) {
                return ResponseHandler.generateResponse("Upload Successfully", HttpStatus.OK, result);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandler.generateResponse("Upload Failed", HttpStatus.BAD_REQUEST, null);
    }

    @ApiOperation(value = "download a file", notes = "download a file")
    @RequestMapping(name = "file-download", path = "*/download",
            method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> fileDownload(HttpServletRequest request,
                                                          @RequestParam(value = "file", required = false) String path,
                                                          HttpServletResponse response
    ) {
        try {
            StorageObject object = googleStorageClientAdapter.download(path);

            byte[] res = Files.toByteArray((File) object.get("file"));
            ByteArrayResource resource = new ByteArrayResource(res);

            return ResponseEntity.ok()
                    .contentLength(res.length)
                    .header("Content-type", "application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\"" + path + "\"").body(resource);
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No such file or directory");
        }
    }
}