package com.card.collector.backend.controller;

import com.card.collector.backend.model.*;
import com.card.collector.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/loginWithFacebook")
    public LoginResponse loginWithFacebook(@RequestParam String accessToken, @RequestParam Integer accountId) {
        logger.debug("[loginWithFacebook] accountId=" + accountId);
        return userService.loginWithFacebook(accessToken, accountId);
    }

    @PostMapping("/signupAnonymous")
    public LoginResponse signupAnonymous() {
        LoginResponse loginResponse = userService.signupAnonymous();
        logger.debug("[signupAnonymous] newAccountCreated=" + loginResponse.getUser().getAccountId());
        return loginResponse;
    }

    @PostMapping("/checkIfAccountExists")
    public LoginResponse checkIfAccountExists(@RequestParam Integer accountId) {
        logger.debug("[checkIfAccountExists] accountId=" + accountId);
        LoginResponse loginResponse = new LoginResponse();
        Account account = userService.getAccountById(accountId);
        if(account != null){
            logger.debug("[checkIfAccountExists] accountExists=true");
            loginResponse.setLoginResult(true);
        } else {
            logger.debug("[checkIfAccountExists] accountExists=false");
            loginResponse.setLoginResult(false);
        }

        return loginResponse;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/test")
    public String test() {
        return "ok";
    }

    @PreAuthorize("hasRole('ROLE_PONCIK')")
    @PostMapping("/test2")
    public String test2() {
        return "ok";
    }
}