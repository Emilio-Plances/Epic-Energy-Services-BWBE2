package com.example.Epic.Energy.Services.requests;

import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.enums.InvoiceStatus;
import com.example.Epic.Energy.Services.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserRequest {

    @NotBlank(message = "date request")
    private String username;

    @NotBlank(message = "email request")
    @Email
    private String email;

    @NotBlank(message = "password request")
    @Pattern(regexp ="^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,}$",
            message = "Password must contain:\n-1 letter uppercase\n-1 letter lowercase\n-1 number\n1 special character")
    private String password;

    @NotBlank(message = "first name request")
    private String firstName;

    @NotBlank(message = "last name request")
    private String lastName;

}
