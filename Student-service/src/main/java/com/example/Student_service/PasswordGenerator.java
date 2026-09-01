package com.example.Student_service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String plainPassword = "test123"; // ye tera naya password hoga, jo yaad rakhna hai
        String hashedPassword = encoder.encode(plainPassword);
        System.out.println(hashedPassword);
    }
}