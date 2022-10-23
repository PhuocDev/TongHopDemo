//package com.example.tonghopdemo.user.repo;
//
//import com.example.tonghopdemo.user.Role;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//class RoleRepositoryTest {
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Test
//    public void testCreateRoles() {
//        Role admin = new Role("ROLE_ADMIN");
//        Role editor = new Role("ROLE_EDITOR");
//        Role customer = new Role("ROLE_CUSTOMER");
//
//        roleRepository.save(admin);
//        roleRepository.save(editor);
//        roleRepository.save(customer);
//
//        long count = roleRepository.count();
//        assertEquals(3, count);
//    }
//}