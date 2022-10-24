package com.example.tonghopdemo;

import com.example.tonghopdemo.bookApi.config.BookAppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties
public class App implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    BookAppProperties bookAppProperties;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Global variable:");
        System.out.println("\t Email: "+bookAppProperties.getDeveloper());

    }
    /*
    Bất kì đâu, khi cần lấy các thông tin config, tôi chỉ cần:
    @Autowired
    BookAppProperties bookAppProperties;
     */
}