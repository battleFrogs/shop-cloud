package org.example.application;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import org.example.common.core.constants.ResultData;
import org.example.common.core.exception.ServiceException;
import org.example.properties.MinioProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UploadApplication {


    @Resource
    private MinioProperties minioProperties;

    public void upload(MultipartFile file, String bucket, String name) {

        try (
                InputStream inputStream = file.getInputStream()
        ) {

            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioProperties.getUrl())
                            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                            .build();


            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(name).stream(
                                    inputStream, -1, 10485760)
                            .contentType("image/jpeg")
                            .build());

        } catch (ServerException | IOException | InsufficientDataException
                | ErrorResponseException | NoSuchAlgorithmException | InvalidKeyException
                | InvalidResponseException | InternalException | XmlParserException e) {
            throw new ServiceException(ResultData.FAIL, "上传失败");
        }

    }



    public String getPictureUrl(String bucket, String name) throws Exception{
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minioProperties.getUrl())
                        .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                        .build();

        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("response-content-type", "image/jpeg");

        return
                minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket(bucket)
                                .object(name)
                                .expiry(2, TimeUnit.HOURS)
                                .extraQueryParams(reqParams)
                                .build());
    }
}
