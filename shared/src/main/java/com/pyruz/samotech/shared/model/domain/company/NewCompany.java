package com.pyruz.samotech.shared.model.domain.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class NewCompany {

    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(name = "title", value = "DanaYar", required = true)
    private String title;

    @NotBlank
    @Size(max = 100)
    @Email
    @ApiModelProperty(name = "email", value ="info@danayar.net", required = true)
    private String email;

    @Size(max = 500)
    @ApiModelProperty(name = "address", value = "Your company address", required = false)
    private String address;

    @Size(max = 100)
    @ApiModelProperty(name = "webSiteURL", value = "http://danayar.net", required = false)
    private String webSiteURL;

    @Size(max = 25)
    @ApiModelProperty(name = "logo", value = "default-logo.png", required = false)
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
    @ApiModelProperty(name = "phone", value = "02188556620", required = false)
    private String phone;

    @Size(min = 10, max = 15)
    @Pattern(regexp = "^([0-9]*)$")
    @ApiModelProperty(name = "fax", value = "02188556620", required = false)
    private String fax;

}
