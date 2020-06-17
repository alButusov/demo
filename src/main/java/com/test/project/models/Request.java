package com.test.project.models;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "APPLICATION_ID")
    private String applicationId;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "DT_CREATED")
    private Instant createdTs;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;

    public Request() {
    }

    public int getId() {
        return id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Instant getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Instant createdTs) {
        this.createdTs = createdTs;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contactId) {
        this.contact = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        return id == request.id &&
            Objects.equals(applicationId, request.applicationId) &&
            Objects.equals(productName, request.productName) &&
            Objects.equals(createdTs, request.createdTs) &&
            Objects.equals(contact, request.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicationId, productName, createdTs, contact);
    }
}
