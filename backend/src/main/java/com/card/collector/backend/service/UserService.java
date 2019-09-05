package com.card.collector.backend.service;

import com.card.collector.backend.model.*;
import com.card.collector.backend.repository.AccountRepository;
import com.card.collector.backend.repository.UserRepository;
import com.card.collector.backend.security.JwtTokenProvider;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FacebookService facebookService;

    private LoginResponse loginViaEmail(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(user);
        try {
            logger.info("New login with email=" + user.getEmail());
            String token = jwtTokenProvider.createToken(user.getEmail(), "ROLE_USER");
            loginResponse.setToken(token);
            loginResponse.setLoginResult(true);
        } catch (Exception e) {
            logger.error("Failed to login for username:" + user.getEmail(), e);
            loginResponse.setLoginResult(false);
            loginResponse.setLoginMessage("loginFailed");
        }
        return loginResponse;
    }

    public LoginResponse loginWithFacebook(String userAccessToken, Integer accountId) {

        LoginResponse loginResponse = new LoginResponse();

        try {
            if(facebookService.validateToken(userAccessToken) == true) {
                JSONObject userInfo = facebookService.getUserInfo(userAccessToken);
                String email = userInfo.getString("email");
                String fullName = userInfo.getString("name");

                // if already registered email, just login
                User user = userRepository.getByEmail(email);
                if (user != null) {
                    return loginViaEmail(user);
                }

                if (accountId == null) {
                    // if new comer, login after signup
                    user = signupNew(fullName, email, "FACEBOOK", SignInType.FACEBOOK.toString());
                    return loginViaEmail(user);
                } else {
                    // if new comer holding an account, login after signup
                    user = signupExistingAccount(accountId, fullName, email, "FACEBOOK", SignInType.FACEBOOK.toString());
                    return loginViaEmail(user);
                }
            }
        } catch (AuthenticationException e) {
            logger.error("Failed to login for token:" + userAccessToken, e);
        }

        loginResponse.setLoginResult(false);
        loginResponse.setLoginMessage("fbLoginFailed");
        return loginResponse;
    }

    private User signupNew(String fullname, String email, String password, String signInType) {
        try {
            Account account = new Account();
            accountRepository.save(account);
            return signupExistingAccount(account.getId(), fullname, email, password, signInType);
        } catch (Exception e) {
            logger.error("Failed to sign up:",e);
            return null;
        }
    }

    private User signupExistingAccount(Integer accountId, String fullname, String email, String password, String signInType) {

        try {
            User user = userRepository.getByAccountId(accountId);
            if(user == null) {
                user = new User();
            }

            user.setEmail(email);
            user.setFullname(fullname);
            user.setSignInType(signInType);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole("ROLE_USER");
            user.setAccountId(accountId);

            if(signInType.equals(SignInType.FACEBOOK.toString()))
                user.setFbValidated(true);
            else
                user.setFbValidated(false);

            userRepository.save(user);
            return user;
        } catch (Exception e) {
            logger.error("Failed to sign up with existing account :",e);
            return null;
        }
    }

    public User findByEmail(String email) {
        try {
            return userRepository.getByEmail(email);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username/password supplied");
        }
    }

    public LoginResponse signupAnonymous() {
        try {
            Account account = new Account();
            accountRepository.save(account);

            String fullName = "user" + account.getId();
            String email = fullName + "@game";
            User user = signupExistingAccount(account.getId(), fullName, email, "PASSWORD", SignInType.REGULAR.toString());

            return loginViaEmail(user);
        } catch (AuthenticationException e) {

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setLoginResult(false);
            loginResponse.setLoginMessage("anonymousSignupFailed");
            return loginResponse;
        }
    }

    public Account getAccountById(Integer accountId){
        return accountRepository.findById(accountId).orElse(null);
    }
}