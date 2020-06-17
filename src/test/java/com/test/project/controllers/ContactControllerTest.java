package com.test.project.controllers;

import java.time.Instant;

import com.test.project.dtos.RequestDto;
import com.test.project.models.Contact;
import com.test.project.models.Request;
import com.test.project.services.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    private static final String APP_ID = "appId";
    private static final String PRODUCT_NAME = "productName";
    private static final String CONTACT_ID = "contactId";
    private static final Instant CREATED_TS = Instant.now();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactService service;

    private Request expectedRequest;

    @Before
    public void setUp() {
        expectedRequest = new Request();
        expectedRequest.setApplicationId(APP_ID);
        expectedRequest.setCreatedTs(CREATED_TS);
        expectedRequest.setProductName(PRODUCT_NAME);

        Contact contact = new Contact();
        contact.setContactId(CONTACT_ID);
        expectedRequest.setContact(contact);

        when(service.getLastRequest(anyString())).thenReturn(expectedRequest);
    }

    @Test
    public void whenGetLastRequestJson_thenReturnJson() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/contacts/contactId/last-json")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.CONTACT_ID", is(CONTACT_ID)))
            .andExpect(jsonPath("$.APPLICATION_ID", is(APP_ID)))
            .andExpect(jsonPath("$.DT_CREATED", is(RequestDto.FORMATTER.format(CREATED_TS))))
            .andExpect(jsonPath("$.PRODUCT_NAME", is(PRODUCT_NAME)));
    }

    @Test
    public void whenGetLastRequestXml_thenReturnXml() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/contacts/contactId/last-xml")
            .contentType(MediaType.APPLICATION_XML))
            .andExpect(status().isOk())
            .andExpect(xpath("/contactRequest").exists())
            .andExpect(xpath("/contactRequest/CONTACT_ID").string(CONTACT_ID))
            .andExpect(xpath("/contactRequest/APPLICATION_ID").string(APP_ID))
            .andExpect(xpath("/contactRequest/DT_CREATED").string(RequestDto.FORMATTER.format(CREATED_TS)))
            .andExpect(xpath("/contactRequest/PRODUCT_NAME").string(PRODUCT_NAME));
    }
}
