package org.example.minio.config;

import io.minio.MinioClient;
import org.example.minio.properties.MyMinioProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(MyMinioProperties.class)
public class MinioConfig {


    @Resource
    private MyMinioProperties minioProperties;


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                        .endpoint(minioProperties.getUrl())
                        .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                        .build();
    }

}
