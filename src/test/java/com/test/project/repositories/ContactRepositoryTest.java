package com.test.project.repositories;

import com.test.project.models.Contact;
import com.test.project.models.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository repository;

    @Test
    public void whenFindFirstByValidContact_thenReturnRequest() {
        Contact found = repository.findByContactId("100");

        assertThat(found).isNotNull();
    }

    @Test
    public void whenFindFirstByContactWithoutRequests_thenReturnNull() {
        Contact found = repository.findByContactId("199");

        assertThat(found).isNull();
    }
}
