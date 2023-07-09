package com.eviro.assessment.grad001.sihlendlovu.enviroassessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eviro.assessment.grad001.sihlendlovu.enviroassessment.entities.AccountProfile;

public interface AccountProfileRepository  extends JpaRepository<AccountProfile, Long>{
    
    @Query("SELECT a FROM AccountProfile a WHERE accountHolderName = ?1 AND accountHolderSurname = ?2")
    AccountProfile findByNameAndSurname(String name, String surname);
}
