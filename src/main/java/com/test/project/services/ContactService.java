package com.test.project.services;

import com.test.project.exception.CustomException;
import com.test.project.models.Contact;
import com.test.project.models.Request;
import com.test.project.repositories.ContactRepository;
import com.test.project.repositories.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final RequestRepository requestRepository;

    public ContactService(ContactRepository contactRepository,
                          RequestRepository requestRepository) {
        this.contactRepository = contactRepository;
        this.requestRepository = requestRepository;
    }

    public Request getLastRequest(String contactId) {
        try {
            Contact contact = contactRepository.findByContactId(contactId);
            if (contact == null) {
                throw new CustomException.NotFoundException("Contact not found by contact_id :" + contactId);
            }

            Request lastRequest = requestRepository.findFirstByContactOrderByCreatedTsDesc(contact);
            if (lastRequest == null) {
                throw new CustomException.NotFoundException("No request found for contact_id :" + contactId);
            }
            return lastRequest;
        } catch (CustomException ce) {
            throw ce;
        } catch (Exception e) {
            throw new CustomException.InternalServerErrorException(e);
        }
    }
}
