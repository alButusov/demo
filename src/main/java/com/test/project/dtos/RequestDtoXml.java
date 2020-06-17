package com.test.project.dtos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contactRequest")
public class RequestDtoXml extends RequestDto {

    public RequestDtoXml() {
    }

    @XmlElement(name = "CONTACT_ID")
    public String getContactId() {
        return contactId;
    }

    @XmlElement(name = "APPLICATION_ID")
    public String getApplicationId() {
        return applicationId;
    }

    @XmlElement(name = "DT_CREATED")
    public String getCreatedTs() {
        return createdTs;
    }

    @XmlElement(name = "PRODUCT_NAME")
    public String getProductName() {
        return productName;
    }
}
