package org.oreochex.file.controllers;

import lombok.RequiredArgsConstructor;
import org.oreochex.file.entities.FileInfo;
import org.oreochex.file.services.FileDeleteService;
import org.oreochex.file.services.FileDownloadService;
import org.oreochex.file.services.FileInfoService;
import org.oreochex.file.services.FileUploadService;
import org.oreochex.global.exceptions.RestExceptionProcessor;
import org.oreochex.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController implements RestExceptionProcessor {

    private final FileUploadService uploadService;
    private final FileDownloadService downloadService;
    private final FileInfoService infoService;
    private final FileDeleteService deleteService;

    @PostMapping("/upload")
    public ResponseEntity<JSONData> upload(@RequestPart("file") MultipartFile[] files, @RequestParam (name="gid", required = false)String gid, @RequestParam(name="location", required = false)String location) {
        List<FileInfo> items = uploadService.upload(files, gid, location);
        HttpStatus status = HttpStatus.CREATED;
        JSONData data = new JSONData(items);
        data.setStatus(status);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @GetMapping("/download/{seq}")
    public void download(@PathVariable("seq") Long seq){
        downloadService.download(seq);
    }

    @DeleteMapping("/delete/{seq}")
    public JSONData delete(@PathVariable("seq") Long seq){
        FileInfo data = deleteService.delete(seq);
        deleteService.delete(seq);

        return new JSONData(data);
    }

    @DeleteMapping("/deletes/{gid}")
    public JSONData deletes(@PathVariable("gid") String gid, @RequestParam(name="location", required = false) String location){
        List<FileInfo> items = deleteService.delete(gid, location);
        return new JSONData(items);
    }


    //파일 개별조회
    @GetMapping("/info/{seq}")
    public JSONData get(@PathVariable("seq") Long seq){
        FileInfo data = infoService.get(seq);
        return new JSONData(data);
    }

    @GetMapping("/list/{gid}")
    public JSONData getList(@PathVariable("gid") String gid, @RequestParam(name="location", required = false) String location){
        List<FileInfo> items = infoService.getList(gid, location);
        return new JSONData(items);
    }


}

