package com.sila.dto.response;

import com.sila.utlis.enums.EnumRole;
import lombok.Data;

@Data
public class AuthResponse {
  private String jwt;
  private String message;
  private EnumRole role;
  private Long userId;
}
