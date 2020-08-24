package com.pyruz.samotech.shared.model.domain.project;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UpdateProject {


    @Digits(fraction = 0, integer = 10, message = "Enter project id")
    @ApiModelProperty(name = "id", value = "1", required = true)
    private Integer id;

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(name = "title", value = "Banking Projects", required = true)
    private String title;

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(name = "caption", value = "Banking Projects", required = true)
    private String caption;

    @NotNull
    @ApiModelProperty(name = "epics", value = "1,2,5,...", required = true)
    private List<Integer> epics;

    @Size(max = 50)
    @ApiModelProperty(name = "backgroundImage", value = "bg.png")
    private String backgroundImage;

    @Size(max = 6)
    @ApiModelProperty(name = "backgroundColor", value = "FF3355")
    private String backgroundColor;

    @NotNull
    @ApiModelProperty(name = "isActive", value = "true/false", required = true)
    private Boolean isActive;
}
