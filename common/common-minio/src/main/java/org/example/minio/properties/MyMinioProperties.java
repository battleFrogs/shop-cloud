package org.example.minio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@Data
@ConfigurationProperties(prefix = "minio")
@RefreshScope
public class MyMinioProperties {

    private String url;
    private String accessKey;
    private String secretKey;

}
