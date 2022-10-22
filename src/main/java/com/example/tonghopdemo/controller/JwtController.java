package com.example.tonghopdemo.controller;

import com.example.tonghopdemo.jwt.JwtTokenProvider;
import com.example.tonghopdemo.payload.LoginRequest;
import com.example.tonghopdemo.payload.LoginResponse;
import com.example.tonghopdemo.payload.RandomStuff;
import com.example.tonghopdemo.user.CustomUserDetails;
import com.example.tonghopdemo.entities.User;
import com.example.tonghopdemo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JwtController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

//    @PostConstruct
//    public void initData() {
//        User user = new User();
//        user.setUsername("loda");
//        user.setPassword(passwordEncoder.encode("loda"));
//        userRepository.save(user);
//    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse authenticateUser( @RequestBody LoginRequest loginRequest) {

        System.out.println("access thành công");
        if (loginRequest == null) System.out.println("login request null");
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }

}
