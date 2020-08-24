package com.pyruz.samotech.shared.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ApplicationUtilities {

    private static ApplicationUtilities uaaUtilities;

    public static ApplicationUtilities getInstance() {
        if (uaaUtilities != null) {
            uaaUtilities = new ApplicationUtilities();
        }
        return uaaUtilities;
    }

    public Pageable pageable(Integer page, ApplicationProperties applicationProperties) {
        if (page == null) return null;
        return new PageRequest(
                page - 1,
                applicationProperties.getCode("page-size"),
                new Sort(Sort.Direction.DESC, "id")
        );
    }

}
