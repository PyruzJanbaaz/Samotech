package com.pyruz.samotech.shared.model.domain.epic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewEpic {

    @NotNull
    @Size(max = 500)
    @ApiModelProperty(name = "title", value = "User management epic", required = true)
    private String title;

    @NotNull
    @ApiModelProperty(name = "projectId", value = "2", required = true)
    private Integer projectId;

    @NotNull
    @ApiModelProperty(name = "state", value = "1", required = true)
    private Integer state;

}
