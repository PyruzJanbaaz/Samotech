package com.pyruz.samotech.shared.model.dto.base;


import com.pyruz.samotech.shared.utility.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaDTO {
    private Integer code;
    private String message;

    public static MetaDTO getInstance(ApplicationProperties applicationProperties) {
        return new MetaDTO(
                applicationProperties.getCode("success-code"),
                applicationProperties.getProperty("success-text")
        );
    }


}
