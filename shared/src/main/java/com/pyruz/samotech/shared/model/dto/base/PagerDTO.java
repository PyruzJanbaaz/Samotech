package com.pyruz.samotech.shared.model.dto.base;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PagerDTO<T> {

    public PagerDTO() {
    }

    public PagerDTO(Page<T> t) {
        this.output = t.getContent();
        this.totalElements = t.getNumberOfElements();
        this.totalPages = t.getTotalPages();
        this.totalRecords = t.getTotalElements();
    }

    public PagerDTO(List<T> output, Integer totalElements, Integer totalPages, Long totalRecords) {
        this.output = output;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
    }

    private List<T> output;
    private Integer totalElements;
    private Integer totalPages;
    private Long totalRecords;
}
