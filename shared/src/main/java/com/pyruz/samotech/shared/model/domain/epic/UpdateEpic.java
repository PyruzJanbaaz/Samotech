package com.pyruz.samotech.shared.model.domain.epic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateEpic {

    @Digits(fraction = 0, integer = 10, message = "Enter epic id")
    @ApiModelProperty(name = "id", value = "1", required = true)
    private Integer id;

    @NotNull
    @Size(max = 500)
    @ApiModelProperty(name = "title", value = "User management epic", required = true)
    private String title;

    @NotNull
    @ApiModelProperty(name = "state", value = "1/2/3 ,...", required = true)
    private Integer state;

    @NotNull
    @ApiModelProperty(name = "isActive", value = "true/false", required = true)
    private Boolean isActive;

}
