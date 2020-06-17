package com.test.project.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "contactRequest")
public class RequestDtoJson extends RequestDto {

    public RequestDtoJson() {
    }

    @JsonGetter(value = "CONTACT_ID")
    public String getContactId() {
        return contactId;
    }

    @JsonGetter(value = "APPLICATION_ID")
    public String getApplicationId() {
        return applicationId;
    }

    @JsonGetter(value = "DT_CREATED")
    public String getCreatedTs() {
        return createdTs;
    }

    @JsonGetter(value = "PRODUCT_NAME")
    public String getProductName() {
        return productName;
    }
}
