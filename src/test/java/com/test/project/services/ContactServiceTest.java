package com.test.project.services;

import java.sql.SQLException;

import com.test.project.exception.CustomException;
import com.test.project.models.Contact;
import com.test.project.models.Request;
import com.test.project.repositories.ContactRepository;
import com.test.project.repositories.RequestRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ContactServiceTest {

    private static final String CONTACT_ID_WITH_REQUEST = "100";
    private static final String CONTACT_ID_WITHOUT_REQUEST = "empty";
    private static final String FOR_INTERNAL_ERROR = "error";

    @Mock
    private ContactRepository contactRepository;
    @Mock
    private RequestRepository requestRepository;

    private Request expectedRequest;
    private ContactService service;

    @Before
    public void setUp() {
        Contact contactWithRequest = new Contact();
        contactWithRequest.setContactId(CONTACT_ID_WITH_REQUEST);
        Mockito.when(contactRepository.findByContactId(CONTACT_ID_WITH_REQUEST))
            .thenReturn(contactWithRequest);

        expectedRequest = new Request();
        expectedRequest.setContact(contactWithRequest);
        Mockito.when(requestRepository.findFirstByContactOrderByCreatedTsDesc(contactWithRequest))
            .thenReturn(expectedRequest);

        Contact contactWithoutRequest = new Contact();
        contactWithoutRequest.setContactId(CONTACT_ID_WITHOUT_REQUEST);
        Mockito.when(contactRepository.findByContactId(CONTACT_ID_WITHOUT_REQUEST))
            .thenReturn(contactWithoutRequest);

        Mockito.when(contactRepository.findByContactId(FOR_INTERNAL_ERROR)).thenThrow(new RuntimeException());

        service = new ContactService(contactRepository, requestRepository);
    }

    @Test
    public void whenContactHasRequests_thenReturnRequest() {
        Request lastRequest = service.getLastRequest(CONTACT_ID_WITH_REQUEST);

        assertThat(lastRequest).isNotNull();
        assertThat(lastRequest).isEqualTo(expectedRequest);
    }

    @Test
    public void whenContactHasNotRequests_thenReturnRequestNotFoundEx() {
        try {
            service.getLastRequest(CONTACT_ID_WITHOUT_REQUEST);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(CustomException.NotFoundException.class);
            assertThat(e.getMessage()).startsWith("No request found for contact_id :");
        }
    }

    @Test
    public void whenContactNotExist_thenReturnContactNotFoundEx() {
        try {
            service.getLastRequest(CONTACT_ID_WITH_REQUEST + 1);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(CustomException.NotFoundException.class);
            assertThat(e.getMessage()).startsWith("Contact not found by contact_id :");
        }
    }

    @Test
    public void whenSmthWrong_thenReturnInternalServerErrorEx() {
        try {
            service.getLastRequest(FOR_INTERNAL_ERROR);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(CustomException.InternalServerErrorException.class);
        }
    }
}
