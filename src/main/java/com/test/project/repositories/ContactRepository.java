package com.test.project.repositories;

import com.test.project.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    Contact findByContactId(String contactId);
}
