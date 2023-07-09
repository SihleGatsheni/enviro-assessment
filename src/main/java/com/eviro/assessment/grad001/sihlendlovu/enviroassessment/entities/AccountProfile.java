package com.eviro.assessment.grad001.sihlendlovu.enviroassessment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AccountProfile {
    @Id
    @GeneratedValue()
    private Long id;
    private String accountHolderName;
    private String accountHolderSurname;
    private String httpImageLink;
    public Long getId() {
        return id;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public String getAccountHolderSurname() {
        return accountHolderSurname;
    }
    public void setAccountHolderSurname(String accountHolderSurname) {
        this.accountHolderSurname = accountHolderSurname;
    }
    public String getHttpImageLink() {
        return httpImageLink;
    }
    public void setHttpImageLink(String httpImageLink) {
        this.httpImageLink = httpImageLink;
    }
    
    
}