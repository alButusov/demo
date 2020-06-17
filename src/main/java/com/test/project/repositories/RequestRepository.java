package com.test.project.repositories;

import com.test.project.models.Contact;
import com.test.project.models.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {

    Request findFirstByContactOrderByCreatedTsDesc(Contact contactId);
}
