package com.sivalabs.geeksclub.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", initialValue = 10)
    @GeneratedValue(generator = "user_generator")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email address is invalid")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password must not be blank")
    private String  password;

    @Column(nullable = false)
    @NotBlank(message = "Name must not be blank")
    private String  name;

    @Column
    private String  website;

    @Column(length = 4000)
    private String  bio;

    @Column(nullable = false)
    private String  role;
}
