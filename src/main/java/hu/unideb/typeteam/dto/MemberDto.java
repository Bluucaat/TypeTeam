package hu.unideb.typeteam.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class MemberDto {
    @NotNull(message = "Username field can not be empty.")
    private String userId;
    @NotNull(message = "Password field should not be empty.")
    private String password;
    @NotNull(message = "Email field can not be empty.")
    private String email;
}
