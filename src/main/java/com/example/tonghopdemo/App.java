package com.example.tonghopdemo;

import com.example.tonghopdemo.bookApi.config.BookAppProperties;
import com.example.tonghopdemo.userJwt.User;
import com.example.tonghopdemo.userJwt.UserRepository;
import com.example.tonghopdemo.userJwt.UserService;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Global variable:");
        System.out.println("\t Email: "+bookAppProperties.getDeveloper());
        User user = new User();
        user.setUsername("loda");
        user.setPassword(passwordEncoder.encode("loda"));
//        user.setPassword("loda");
        userRepository.save(user);
        //userService.save(user);
        System.out.println(user.getPassword());
        System.out.println(userRepository.findUserByUsername("loda").getPassword());
    }
    /*
    Bất kì đâu, khi cần lấy các thông tin config, tôi chỉ cần:
    @Autowired
    BookAppProperties bookAppProperties;
     */
}