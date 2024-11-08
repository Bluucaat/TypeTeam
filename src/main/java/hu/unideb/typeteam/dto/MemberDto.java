package hu.unideb.typeteam.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class MemberDto {
    @NotEmpty(message = "UserName can not be empty.")
    private String userId;
    @NotEmpty(message = "Password field can not be empty")
    private String password;
    @NotEmpty(message = "email can not be empty.")
    private String email;
    @NotEmpty(message = "please confirm the password")
    private String passwordConfirm;

    @AssertTrue(message = "Passwords do not match.")
    public boolean isPasswordMatching() {
        return password.equals(passwordConfirm);
    }
}
