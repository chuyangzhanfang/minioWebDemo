package com.jiantou.demo.minio;

import com.jiantou.demo.config.MinioParamConfig;
import com.jiantou.demo.util.R;
import io.minio.MinioClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class MinioDownload {
    public static MinioClient minioClient;

    @Resource
    private MinioParamConfig paramConfig ;

    public R getDownloadUrl(String remoteFileName) {

        try {
            minioClient = new MinioClient(
                    paramConfig.getUrl(),
                    paramConfig.getAccessKey(),
                    paramConfig.getSecretKey());

            String property = paramConfig.getExpirationSeconds();
            int parseInt = Integer.parseInt(property);
            int seconds = 60 * 60 * 24 * parseInt;
            String url = minioClient.presignedGetObject(paramConfig.getBucket(), "/" + remoteFileName, seconds);
            if (url == null) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return R.ok().put("downloadUrl", url);
        } catch (Exception e) {
            return R.error("出现异常！");
        }
    }
}
