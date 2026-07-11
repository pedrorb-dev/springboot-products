package com.project.products.springboot_products.controllers;

import com.project.products.springboot_products.services.JwtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/login-cajero")
    public String loginCajero() {
        return jwtService.generatedKey("pedro_cajero", List.of("ROLE_CAJERO"));
    }

    @GetMapping("/login-admin")
    public String loginAdmin() {
        return jwtService.generatedKey("pedro_admin", List.of("ROLE_ADMIN"));
    }
}
