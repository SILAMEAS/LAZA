package com.sila.dto.response;

import com.sila.utlis.enums.EnumRole;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserRes implements Serializable {
    private String profile;
    private String fullName;
    private String email;
    private EnumRole role;
//    private List<Address> addresses;
}
