package com.jiantou.demo.controller;

import com.jiantou.demo.service.MinioFileService;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import com.jiantou.demo.util.R;
import io.minio.MinioClient;
import io.minio.messages.Item;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/files")
public class MinioController {

    @Autowired
    private MinioService minioService;

    @Autowired
    private MinioFileService minioFileService;

    @Autowired
    private MinioClient minioClient;

    /**
     * 文件列表
     */
    @ApiOperation(value="获取文件列表", notes="测试MinIO文件列表")
    @GetMapping("/list")
    public R list() throws MinioException {
        List<Item> fileLists = minioService.list();
        return R.ok().put("files", fileLists);
    }

    /*@ApiOperation(value="获取文件", notes="测试MinIO文件获取")
    @GetMapping("/{object}")
    public void getObject(@PathVariable("object") String object, HttpServletResponse response) throws MinioException, IOException {
        InputStream inputStream = minioService.get(Paths.get(object));

        response.addHeader("Content-disposition", "attachment;filename=" + object);
        response.setContentType(URLConnection.guessContentTypeFromName(object));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }*/

    /**
     * 文件上传
     */
    @ApiOperation(value="上传文件", notes="测试MinIO文件上传")
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) {
        Path path = Paths.get(file.getOriginalFilename());
        try {
            minioService.upload(path, file.getInputStream(), file.getContentType());
        } catch (MinioException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        } catch (IOException e) {
            throw new IllegalStateException("The file cannot be read", e);
        }
        return R.ok();
    }

    /**
     * 获取URL下载地址
     * remoteFileName：minio上的文件名称
     */
    @ApiOperation(value="获取URL下载地址", notes="获取URL下载地址")
    @RequestMapping(value = "/getDownloadUrl", method = RequestMethod.GET)
    public R getDownloadUrl(@RequestParam("remoteFileName") String remoteFileName) {
        return minioFileService.getDownloadUrl(remoteFileName);
    }
}
