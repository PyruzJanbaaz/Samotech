package com.pyruz.samotech.shared.model.domain.collection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UpdateCollection {


    @Digits(fraction = 0, integer = 10, message = "Enter collection id")
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
    @ApiModelProperty(name = "projects", value = "1,2,5,...", required = true)
    private List<Integer> projects;

    @NotNull
    @ApiModelProperty(name = "isActive", value = "true/false", required = true)
    private Boolean isActive;
}
