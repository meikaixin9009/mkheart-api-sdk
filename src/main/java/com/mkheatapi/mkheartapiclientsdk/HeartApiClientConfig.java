package com.mkheatapi.mkheartapiclientsdk;


import com.mkheatapi.mkheartapiclientsdk.client.HeartApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mkheart.client")
@Data
@ComponentScan
public class HeartApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public HeartApiClient heartpiClient() {

        return new HeartApiClient(accessKey,secretKey);

    }


}
