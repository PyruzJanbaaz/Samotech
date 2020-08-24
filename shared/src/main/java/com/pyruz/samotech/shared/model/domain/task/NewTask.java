package com.pyruz.samotech.shared.model.domain.task;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewTask {

    @NotNull
    @Size(max = 500)
    @ApiModelProperty(name = "title", value = "Create login form in UI", required = true)
    private String title;

    @NotNull
    @ApiModelProperty(name = "state", value = "1/2/3 ,...", required = true)
    private Integer state;

}
