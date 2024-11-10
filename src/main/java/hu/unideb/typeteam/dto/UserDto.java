package hu.unideb.typeteam.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class UserDto {
    @NotEmpty(message = "UserName can not be empty.")
    private String userId;
    @NotEmpty(message = "Password field can not be empty")
    private String password;
    @NotEmpty(message = "email can not be empty.")
    @Email(message = "incorrect email given.")
    private String email;
    @NotEmpty(message = "please confirm the password")
    private String passwordConfirm;
}
