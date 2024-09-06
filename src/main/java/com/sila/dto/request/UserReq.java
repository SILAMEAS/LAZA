package com.sila.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserReq {
    private String profile;
    private String fullName;
//    private List<Address> addresses;
}
