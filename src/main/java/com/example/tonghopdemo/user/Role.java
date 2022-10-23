package com.example.tonghopdemo.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 60)
    private String name;

    public Role(String roleName) {
        this.name = roleName;
    }

    public Role() {

    }
}