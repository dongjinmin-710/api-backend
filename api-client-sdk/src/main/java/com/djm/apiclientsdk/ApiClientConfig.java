package com.djm.apiclientsdk;

import com.djm.apiclientsdk.client.ApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Api 客户端配置
 *
 */
@Configuration
@ConfigurationProperties("djm.api.client")
@Data
@ComponentScan
public class ApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public ApiClient ApiClient() {
        return new ApiClient(accessKey, secretKey);
    }

}