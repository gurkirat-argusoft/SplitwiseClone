package com.splitwise.clone.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SecurityController {
    @RequestMapping("/forgotpassword")
    public void forgotPassword(){
    
    }

    @RequestMapping("/signup")
    public void signup(){

    }

    @RequestMapping("/login")
    public void login(){

    }

    @RequestMapping("/logout")
    public void logout(){
        
    }
}
