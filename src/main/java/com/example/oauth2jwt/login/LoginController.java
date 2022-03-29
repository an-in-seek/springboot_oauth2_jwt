package com.example.oauth2jwt.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 로그인
 */
@Controller
public class LoginController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
