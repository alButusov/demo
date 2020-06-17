package com.test.project.controllers;

import com.test.project.dtos.RequestDto;
import com.test.project.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/{contact_id}/last-xml", produces = "application/xml")
    public RequestDto getLastRequestXml(@PathVariable("contact_id") String contactId) {
        return RequestDto.toXmlDto(contactService.getLastRequest(contactId));
    }

    @GetMapping(value = "/{contact_id}/last-json", produces = "application/json")
    public RequestDto getLastRequestJson(@PathVariable("contact_id") String contactId) {
        return RequestDto.toJsonDto(contactService.getLastRequest(contactId));
    }
}
