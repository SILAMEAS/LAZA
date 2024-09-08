package com.sila.dto.request;

import com.sila.utlis.enums.EnumRole;
import com.sila.utlis.validateEnum.ValidEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpReq extends LoginReq{
    @NotNull(message = "fullName can't be null")
    @Schema(name = "fullName",example = "Jonh sila",defaultValue = "Meas sila")
    private String fullName;
    @NotNull(message = "Status must not be null")
    @ValidEnum(enumClass = EnumRole.class, message = "Invalid status value. accept only {ROLE_USER , ROLE_ADMIN}")
    private String role;
}
