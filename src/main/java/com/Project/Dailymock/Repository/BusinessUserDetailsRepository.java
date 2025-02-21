package com.Project.Dailymock.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Dailymock.Entity.BusinnessUserDetails;
@Repository
public interface BusinessUserDetailsRepository extends JpaRepository<BusinnessUserDetails, Integer> {

	static BusinnessUserDetails findByEmailAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	static BusinnessUserDetails findByMobileAndPassword(Long mobile, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 Optional<BusinnessUserDetails> findByName(String name);
	 
	 List<BusinnessUserDetails> findByRole(String role);
	 
	 Optional<BusinnessUserDetails> findByEmail(String email);
	
}
