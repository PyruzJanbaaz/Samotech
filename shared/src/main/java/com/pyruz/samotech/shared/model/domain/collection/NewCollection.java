package com.pyruz.samotech.shared.model.domain.collection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewCollection {

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(name = "title", value = "Banking Projects", required = true)
    private String title;

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(name = "caption", value = "Banking Projects", required = true)
    private String caption;

}
