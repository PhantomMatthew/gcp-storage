package com.test.gcpstorage.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
public class FileTypeChecker {
    public static String identifyFileContentType(final String fileName)
    {
        String fileType = "undefined";
        final File file = new File(fileName);

        try {
            fileType = Files.probeContentType(file.toPath());
            log.info(fileType);
        }
        catch (IOException ioException)
        {
            log.error(ioException.toString());
        }

        return fileType;
    }
}
