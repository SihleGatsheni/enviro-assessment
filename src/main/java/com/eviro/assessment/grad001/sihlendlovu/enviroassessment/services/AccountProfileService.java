package com.eviro.assessment.grad001.sihlendlovu.enviroassessment.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.eviro.assessment.grad001.sihlendlovu.enviroassessment.entities.AccountProfile;
import com.eviro.assessment.grad001.sihlendlovu.enviroassessment.repositories.AccountProfileRepository;

@Service
public class AccountProfileService {
    
    private AccountProfileRepository accountProfileRepository;

    public AccountProfileService(AccountProfileRepository accountProfileRepository) {
        this.accountProfileRepository = accountProfileRepository;
        
    }

    public List<AccountProfile> getAllProfiles() {
        return accountProfileRepository.findAll();
    }

    public void createProfile(AccountProfile accountProfile) {
        accountProfileRepository.save(accountProfile);
    }
    public void createProfiles(List<AccountProfile> accountProfiles) {
        accountProfileRepository.saveAll(accountProfiles);
    }

    public AccountProfile getProfileByNameAndSurname(String name, String surname) {
        return accountProfileRepository.findByNameAndSurname(name, surname);
    }
}
