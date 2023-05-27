package ru.example.java.spring.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "encoder-variable")
//@PropertySource(value = "classpath:encoder-variable.yml", factory = YamlPropertySourceFactory.class)
public class EncoderProperties {

    private String secret;
    private Integer iteration;
    private Integer keylength;
    private String algorithm;

}
