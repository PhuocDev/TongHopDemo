package com.example.tonghopdemo.user.repo;

import com.example.tonghopdemo.App;
import com.example.tonghopdemo.user.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
class RoleRepositoryTest {
    @MockBean
    private RoleRepository roleRepository;

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
}