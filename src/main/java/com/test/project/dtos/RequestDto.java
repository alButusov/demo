package com.test.project.dtos;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import com.test.project.models.Request;

public abstract class RequestDto {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        .withLocale(Locale.ENGLISH)
        .withZone(ZoneId.systemDefault());

    protected String contactId;
    protected String applicationId;
    protected String createdTs;
    protected String productName;

    protected RequestDto() {
    }

    public abstract String getContactId();

    public abstract String getApplicationId();

    public abstract String getCreatedTs();

    public abstract String getProductName();

    private void setContactId(String contactId) {
        this.contactId = contactId;
    }

    private void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    private void setCreatedTs(String createdTs) {
        this.createdTs = createdTs;
    }

    private void setProductName(String productName) {
        this.productName = productName;
    }

    public static RequestDto toJsonDto(Request request) {
        return initAndReturn(new RequestDtoJson(), request);
    }

    public static RequestDto toXmlDto(Request request) {
        return initAndReturn(new RequestDtoXml(), request);
    }

    private static RequestDto initAndReturn(RequestDto dto, Request request) {
        dto.setContactId(request.getContact().getContactId());
        dto.setApplicationId(request.getApplicationId());
        dto.setCreatedTs(FORMATTER.format(request.getCreatedTs()));
        dto.setProductName(request.getProductName());

        return dto;
    }
}
