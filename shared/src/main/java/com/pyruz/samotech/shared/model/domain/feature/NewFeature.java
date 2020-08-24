package com.pyruz.samotech.shared.model.domain.feature;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewFeature {

    @NotNull
    @Size(max = 500)
    @ApiModelProperty(name = "title", value = "Login feature", required = true)
    private String title;

    @NotNull
    @ApiModelProperty(name = "state", value = "1/2/3 ,...", required = true)
    private Integer state;

}
