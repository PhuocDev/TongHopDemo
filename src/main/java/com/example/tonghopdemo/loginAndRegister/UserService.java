package com.example.tonghopdemo.loginAndRegister;


import com.example.tonghopdemo.loginAndRegister.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else return new CustomUserDetails(user);
    }

    //    public UserDetails loadUserById(Long userId) {
//        return (UserDetails) userRepository.findById(userId).get();
//    }
    public void save(User user) {
        userRepository.save(user);
    }
}