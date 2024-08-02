package org.oreochex.file.controllers;

import lombok.RequiredArgsConstructor;
import org.oreochex.file.entities.FileInfo;
import org.oreochex.file.services.FileUploadService;
import org.oreochex.global.exceptions.RestExceptionProcessor;
import org.oreochex.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController implements RestExceptionProcessor {

    private final FileUploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<JSONData> upload(@RequestPart("file") MultipartFile[] files, @RequestParam (name="gid", required = false)String gid, @RequestParam(name="location", required = false)String location) {
        List<FileInfo> items = uploadService.upload(files, gid, location);
        HttpStatus status = HttpStatus.CREATED;
        JSONData data = new JSONData(items);
        data.setStatus(status);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
    private String getGid(){
        return UUID.randomUUID().toString();
    }
}

