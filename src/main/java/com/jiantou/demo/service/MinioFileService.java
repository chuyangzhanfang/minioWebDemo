package com.jiantou.demo.service;


import com.jiantou.demo.util.R;

public interface MinioFileService {


    /*public ResultInfo<String> savefile(MultipartFile file) {
        File fileToSave = null;
        ResultInfo<String> resultInfo = new ResultInfo<String>();
        try {
            fileToSave = new File(file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            FileCopyUtils.copy(bytes, fileToSave);
            String remoteFileName = UUID.randomUUID() + file.getOriginalFilename();
            String path = minioUpload.uploadToMinio(fileToSave, "img", remoteFileName);
            String data = "video" + "," + remoteFileName;
            resultInfo.setData(data);
            resultInfo.setSuccess(true);
            resultInfo.setMessage(path);
        } catch (Exception e) {
            // TODO: handle exception
            resultInfo.setSuccess(false);
            resultInfo.setMessage(e.getMessage());
        } finally {
            if (fileToSave != null) {
                fileToSave.delete();
            }
        }
        return resultInfo;
    }*/

    public R getDownloadUrl(String remoteFileName);
}
