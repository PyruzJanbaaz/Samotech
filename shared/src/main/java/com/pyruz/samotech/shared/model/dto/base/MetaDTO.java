package com.pyruz.samotech.shared.model.dto.base;


import com.pyruz.samotech.shared.utility.ApplicationProperties;
import lombok.Data;

@Data
public class MetaDTO {

    private Integer code;
    private String message;

    public static MetaDTO getInstance(ApplicationProperties applicationProperties) {
        return new MetaDTO(
                applicationProperties.getCode("success-code"),
                applicationProperties.getProperty("success-text")
        );
    }

    public MetaDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MetaDTO() {
    }

}
