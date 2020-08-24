package com.pyruz.samotech.shared.model.domain.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NewRole {

    @NotBlank
    @Size(max = 50)
    @ApiModelProperty(name = "title", value = "Administrator", required = true)
    private String title;

    @NotBlank
    @Size(max = 50)
    @ApiModelProperty(name = "caption", value = "Administrator", required = true)
    private String caption;

}
