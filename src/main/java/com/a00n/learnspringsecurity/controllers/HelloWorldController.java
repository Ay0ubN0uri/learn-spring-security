package com.a00n.learnspringsecurity.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.a00n.learnspringsecurity.entities.MyUserDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/whoami")
    public String whoami() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println(authentication.getPrincipal());
            MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
            return "Hello " + user.getFullName() + " Roles : " + user.getAuthorities();
        } else {
            return "Not authenticated";
        }
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello admin";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello user";
    }

    @GetMapping("/manager")
    public String manager() {
        return "Hello manager";
    }

}
