package com.Project.Dailymock.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Dailymock.Entity.BusinessDetails;
@Repository
public interface BusinessDetailsRepository extends JpaRepository<BusinessDetails, Integer> {

	Optional<BusinessDetails> findById(Integer id);

    List<BusinessDetails> findByState(String state);

    List<BusinessDetails> findByCity(String city);

    List<BusinessDetails> findBySector(String sector);

 // Query to find BusinessDetails by businessname
    List<BusinessDetails> findByBusinessname(String businessname);

    // Query to find BusinessDetails by subsector
    List<BusinessDetails> findBySubsector(String subsector);
}
