package com.example.tonghopdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "book")
public class BookAppProperties {
    private String developer;
}
/*  Ví dụ:
loda:
  email: loda.namnh@gmail.com
  googleAnalyticsId: U-xxxxx
  authors:
    - loda
    - atom
  exampleMap:
    key1: hello
    key2: world

@Override
public void run(String... args) throws Exception {
    System.out.println("Global variable:");
    System.out.println("\t Email: " + lodaAppProperties.getEmail());
    System.out.println("\t GA ID: " + lodaAppProperties.getGoogleAnalyticsId());
    System.out.println("\t Authors: " + lodaAppProperties.getAuthors());
    System.out.println("\t Example Map: " + lodaAppProperties.getExampleMap());
}

Trong trường hợp cần chạy nhiều môi trường, có thể tham khảo:
https://loda.me/articles/sb17-chay-nhieu-moi-truong-voi-spring-profile
 */
