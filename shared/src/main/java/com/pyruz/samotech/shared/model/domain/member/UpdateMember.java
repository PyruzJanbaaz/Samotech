package com.pyruz.samotech.shared.model.domain.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.List;

@Data
public class UpdateMember {

    @Digits(fraction = 0, integer = 10, message = "123e4567-e89b-42d3-a456-556642440")
    @ApiModelProperty(name = "id", value = "1", required = true)
    private Integer id;

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(name = "firstName", value = "Pyruz", required = true)
    private String firstName;

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(name = "lastName", value = "Janbaaz", required = true)
    private String lastName;

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(name = "username", value = "Pyruz", required = true)
    private String username;

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(name = "password", value = "********", required = true)
    private String password;

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(name = "confirmPassword", value = "********", required = true)
    private String confirmPassword;

    @NotNull
    @Size(max = 50)
    @Email
    @ApiModelProperty(name = "email", value = "info@danayar.net", required = true)
    private String email;

    @Size(max = 50)
    @ApiModelProperty(name = "avatar", value = "logo.png")
    private String avatar;

    @Size(max = 250)
    @ApiModelProperty(name = "address", value = "Giulan Province, Rasht, Lakan Blvd")
    private String address;

    @NotNull
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")
    @ApiModelProperty(name = "birthday", value = "1985/11/25", required = true)
    private String birthday;

    @Size(max = 10, min = 10)
    @Pattern(regexp = "^([0-9]*)$")
    @ApiModelProperty(name = "nationalCode", value = "0023412549", required = true)
    private String nationalCode;

    @Size(max = 10, min = 10)
    @Pattern(regexp = "^([0-9]*)$")
    @ApiModelProperty(name = "postalCode", value = "1752400896", required = true)
    private String postalCode;

    @NotNull
    @ApiModelProperty(name = "roles", value = "1,2,5,...", required = true)
    private List<Integer> roles;

    @NotNull
    @ApiModelProperty(name = "companyId", value = "3", required = true)
    private Integer companyId;

    @NotNull
    @ApiModelProperty(name = "isActive", value = "true/false", required = true)
    private Boolean isActive;
}
