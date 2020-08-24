package com.pyruz.samotech.shared.model.domain.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
@Data
public class UpdateCompany {

    @Digits(fraction = 0, integer = 10, message = "add a digit msg")
    @ApiModelProperty(name = "id", value = "1", required = true)
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(name = "title", value = "DanaYar", required = true)
    private String title;

    @NotBlank
    @Size(max = 100)
    @Email
    @ApiModelProperty(name = "email", value = "info@danayar.net", required = true)
    private String email;

    @Size(max = 500)
    @ApiModelProperty(name = "address", value = "Your company address")
    private String address;

    @Size(max = 100)
    @ApiModelProperty(name = "webSiteURL", value = "http://danayar.net")
    private String webSiteURL;

    @Size(max = 25)
    @ApiModelProperty(name = "logo", value = "logo.png")
    private String logo = "default-logo.png";

    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^([0-9]*)$")
    @ApiModelProperty(name = "economicCode", value = "3200125525", required = true)
    private String economicCode;

    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^([0-9]*)$")
    @ApiModelProperty(name = "postalCode", value = "4510236952", required = true)
    private String postalCode;

    @Size(min = 10, max = 15)
    @Pattern(regexp = "^([0-9]*)$")
    @ApiModelProperty(name = "phone", value = "02188556620")
    private String phone;

    @Size(min = 10, max = 15)
    @Pattern(regexp = "^([0-9]*)$")
    @ApiModelProperty(name = "fax", value = "02188556620")
    private String fax;

    @NotNull
    @ApiModelProperty(name = "isActive", value = "true/false", required = true)
    private Boolean isActive;


}
