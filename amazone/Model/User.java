package org.example.amazone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Data
public class User {
    @NotNull(message = "the id cannot be empty")
    private  Integer id;
    @NotEmpty(message = "the name cannot be empty")
    @Length(min = 6,message = "the length should be more than 5")
    private String userName;
    @NotEmpty(message = "the password cannot be empty ")
    @Length(min = 6,message = "the password should be at least 6 char")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$" ,message = "the password should be letter and numbers")
    private String password;
    @NotEmpty(message = "the email cannot be empty ")
    @Email
    private String email;
    @NotEmpty(message = "the role cannot be empty")
    @Pattern(regexp = "(admin|customer)" ,message = "the role should be admin OR customer")
    private String role;
    @NotNull(message = "the balance cannot be empty")
    @Positive
    private double balance;
}
