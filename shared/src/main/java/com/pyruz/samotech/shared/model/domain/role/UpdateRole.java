package com.pyruz.samotech.shared.model.domain.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateRole {

    @Digits(fraction = 0, integer = 10, message = "Enter role id")
    @ApiModelProperty(name = "id", value = "1", required = true)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    @ApiModelProperty(name = "title", value = "Administrator", required = true)
    private String title;

    @NotBlank
    @Size(max = 50)
    @ApiModelProperty(name = "caption", value = "Administrator", required = true)
    private String caption;

    @NotNull
    @ApiModelProperty(name = "isActive", value = "true/false", required = true)
    private Boolean isActive;

}
