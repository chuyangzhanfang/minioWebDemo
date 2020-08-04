package com.jiantou.demo.service.impl;

import com.jiantou.demo.minio.MinioDownload;
import com.jiantou.demo.service.MinioFileService;
import com.jiantou.demo.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinioFileServiceImpl implements MinioFileService {

    @Autowired
    MinioDownload minioDownload;

    public R getDownloadUrl(String remoteFileName) {

        R downloadUrl = minioDownload.getDownloadUrl(remoteFileName);
        return downloadUrl;
    }
}
