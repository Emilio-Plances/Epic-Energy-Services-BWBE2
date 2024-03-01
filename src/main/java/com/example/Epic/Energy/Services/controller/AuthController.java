package com.example.Epic.Energy.Services.controller;

import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.entities.User;
import com.example.Epic.Energy.Services.exceptions.BadRequestExceptionHandler;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.requests.RegisterRequest;
import com.example.Epic.Energy.Services.requests.SendMessageRequest;
import com.example.Epic.Energy.Services.responses.DefaultResponse;
import com.example.Epic.Energy.Services.responses.LoginResponse;
import com.example.Epic.Energy.Services.security.JwtTools;
import com.example.Epic.Energy.Services.requests.LoginRequest;
import com.example.Epic.Energy.Services.services.CustomerService;
import com.example.Epic.Energy.Services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;
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

    @PostMapping("/sendEmail")
    public ResponseEntity<DefaultResponse> sendEmailToContact(@RequestBody SendMessageRequest request,
                                                              @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String adminUsername = extractAdminUsernameFromToken(auth);
        try {
            Customer customer = customerService.getCustomerByEmail(request.getDestinatario());
            customer.setLastContactDate(LocalDate.now());
            customerService.saveCustomer2(customer);
        } catch (NotFoundException e) {
            return DefaultResponse.noObject("Customer not found", HttpStatus.NOT_FOUND);
        }
        sendEmailFromAdmin(request.getDestinatario(), request.getOggetto(), request.getMessaggio(), adminUsername);
        return DefaultResponse.noMessage("Email sent successfully", HttpStatus.OK);
    }

    private String extractAdminUsernameFromToken(String authHeader) {
        String token = authHeader.substring(7);
        return jwtTools.extractUsername(token);
    }

    private void sendEmailFromAdmin(String recipientEmail, String subject, String message, String adminUsername) {
        String messageWithAdminUsername = message + "\n\nAdmin: " + adminUsername;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(messageWithAdminUsername);

        mailSender.send(simpleMailMessage);
    }


    private void sendEmail(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Thank you for subscribe");
        simpleMailMessage.setText("Thank you very GRAZIE!");
        mailSender.send(simpleMailMessage);
    }





}

