package com.test.project.repositories;

import com.test.project.models.Contact;
import com.test.project.models.Request;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RequestRepositoryTest {

    @Autowired
    private RequestRepository repository;

    @Test
    public void whenFindFirstByValidContact_thenReturnRequest() {
        Contact contact = new Contact();
        contact.setContactId("100");

        Request found = repository.findFirstByContactOrderByCreatedTsDesc(contact);

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(2);
    }

    @Test
    public void whenFindFirstByContactWithoutRequests_thenReturnNull() {
        Contact contact = new Contact();
        contact.setContactId("empty");

        Request found = repository.findFirstByContactOrderByCreatedTsDesc(contact);

        assertThat(found).isNull();
    }
}
