package com.example.Epic.Energy.Services.controller;

import com.example.Epic.Energy.Services.entities.User;
import com.example.Epic.Energy.Services.exceptions.BadRequestExceptionHandler;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.requests.RegisterRequest;
import com.example.Epic.Energy.Services.responses.DefaultResponse;
import com.example.Epic.Energy.Services.responses.LoginResponse;
import com.example.Epic.Energy.Services.security.JwtTools;
import com.example.Epic.Energy.Services.requests.LoginRequest;
import com.example.Epic.Energy.Services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtTools jwtTools;
    @PostMapping("/register")
    public ResponseEntity<DefaultResponse> register(@RequestBody @Validated RegisterRequest registerRequest, BindingResult bindingResult) throws BadRequestExceptionHandler {
        if(bindingResult.hasErrors()){
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }

        sendEmail(registerRequest.getEmail());
        return DefaultResponse.noMessage(userService.saveUser(registerRequest), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) throws BadRequestExceptionHandler, NotFoundException {
        if(bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        User user=userService.findByUsername(loginRequest.getUsername());
        if(!encoder.matches(loginRequest.getPassword(), user.getPassword())) throw new BadRequestExceptionHandler("Password errata");
        String token= jwtTools.createToken(user);
        return LoginResponse.login(token,user,HttpStatus.OK);
    }
    private void sendEmail(String email){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Thank you for subscribe");
        simpleMailMessage.setText("Thank you very GRAZIE!");
        mailSender.send(simpleMailMessage);
    }
}
