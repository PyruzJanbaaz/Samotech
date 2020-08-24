package com.pyruz.samotech.shared.model.dto.base;

import lombok.Data;

@Data
public class BaseDTO<T> {

    private MetaDTO meta;
    private T data;

    public BaseDTO() {
    }

    //--> Constructors
    public BaseDTO(MetaDTO meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public BaseDTO(MetaDTO meta) {
        this.meta = meta;
    }



}
