package org.example.web;

import org.example.application.UploadApplication;
import org.example.common.core.constants.ResultData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class UploadController {

    @Resource
    private UploadApplication uploadApplication;



    /**
     * 图片上传接口
     *
     * @param file   文件
     * @param bucket 存储桶
     * @param name   文件名称（带后缀）
     * @return resultData
     */
    @RequestMapping("/upload")
    public ResultData<Object> upload(@RequestParam("file") MultipartFile file,
                                     @RequestParam("bucket") String bucket,
                                     @RequestParam("name") String name) {
        uploadApplication.upload(file, bucket, name);
        return ResultData.ok(null, "成功");
    }



    /**
     * 获取图片文件路径
     *
     * @param bucket 存储桶
     * @param name   文件名称（带后缀）
     * @return resultData
     */
    @RequestMapping("/getPictureUrl")
    public ResultData<String> getPictureUrl(@RequestParam("bucket") String bucket,
                                            @RequestParam("name") String name) throws Exception {
        String url = uploadApplication.getPictureUrl(bucket, name);
        return ResultData.ok(url, "成功");
    }


}
