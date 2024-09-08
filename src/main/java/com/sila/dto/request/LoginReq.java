package com.sila.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoginReq {
    @NotNull(message = "email can't be null")
    @Schema(name = "email",example = "admin@gmail.com",defaultValue = "sila@gmail.com")
    private String email;
    @NotNull(message = "password can't be null")
    @Schema(name = "password",example = "admin123",defaultValue = "sila123")
    private String password;
}
