package com.example.tonghopdemo.loginAndRegister.controller;

import com.example.tonghopdemo.loginAndRegister.CustomUserDetails;
import com.example.tonghopdemo.loginAndRegister.Role;
import com.example.tonghopdemo.loginAndRegister.User;
import com.example.tonghopdemo.loginAndRegister.jwt.JwtTokenUtil;
import com.example.tonghopdemo.loginAndRegister.payload.LoginDto;
import com.example.tonghopdemo.loginAndRegister.payload.SignUpDto;
import com.example.tonghopdemo.loginAndRegister.repo.RoleRepository;
import com.example.tonghopdemo.loginAndRegister.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil tokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        System.out.println(loginDto);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateAccessToken(userRepository.findByUsername(loginDto.getUsernameOrEmail()).get());
        String role = userRepository.findByUsername(loginDto.getUsernameOrEmail()).get().getRoles().toString();
        return new ResponseEntity<>(role + " Login successfully! Token: " + jwt, HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        System.out.println(signUpDto.getUsername());
        System.out.println(signUpDto.getPassword());
        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        String role = "";

        //Cú pháp tạm thời để đúng định dạng của role
        if (signUpDto.getRole().equals("ROLE_ADMIN")) {
            role = "ROLE_ADMIN";
        } else {
            role = "ROLE_USER";
        }
        //System.out.println("signup dto role = " + signUpDto.getRole()  + " actually: " + role);
        Role roles = roleRepository.findByName(role).get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<>(role + " registered successfully", HttpStatus.OK);
    }


    @RolesAllowed({"ROLE_ADMIN"})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<?> showAdminPage() {
        return new ResponseEntity<>("Only admin can see this", HttpStatus.OK);
    }
    @RolesAllowed({"ROLE_USER"})
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<?> showUserPage() {
        return new ResponseEntity<>("Only user can see this", HttpStatus.OK);
    }
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/index")
    public ResponseEntity<?> showCommonPage() {
        return new ResponseEntity<>("Admin and user can both see this", HttpStatus.OK);
    }
}
