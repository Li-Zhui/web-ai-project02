package com.aliyun.oss;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    public String endpoint;
    public String bucketName;
    public String region;
}
