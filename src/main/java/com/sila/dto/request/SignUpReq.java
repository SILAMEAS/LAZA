package com.sila.dto.request;

import com.sila.utlis.enums.EnumRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpReq {
    private String fullName;
    private String email;
    private String password;
    private EnumRole role;
}
