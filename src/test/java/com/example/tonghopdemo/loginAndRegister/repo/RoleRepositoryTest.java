package com.example.tonghopdemo.loginAndRegister.repo;

import com.example.tonghopdemo.App;
import com.example.tonghopdemo.loginAndRegister.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
class RoleRepositoryTest {
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private UserRepository userRepository;
//    @MockBean
//    private User user;

    @Test
    public void testCreateRoles() {
        Role admin = new Role("ROLE_ADMIN");
        Role editor = new Role("ROLE_EDITOR");
        Role customer = new Role( "ROLE_CUSTOMER");

        when(roleRepository.findAll()).thenReturn(
                Stream.of(
                        new Role("ROLE_ADMIN"),
                        new Role("ROLE_EDITOR"),
                        new Role( "ROLE_CUSTOMER")
                ).collect(Collectors.toList())
        );
        int count = roleRepository.findAll().size();
        assertEquals(3, count);
    }
    @Test
    public void testAssignRoleToUser() {

    }
}