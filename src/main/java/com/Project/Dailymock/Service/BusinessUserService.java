package com.Project.Dailymock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Project.Dailymock.Entity.BusinessDetails;
import com.Project.Dailymock.Entity.BusinnessUserDetails;
import com.Project.Dailymock.Repository.BusinessDetailsRepository;
import com.Project.Dailymock.Repository.BusinessUserDetailsRepository;

import jakarta.transaction.Transactional;



@Service
public class BusinessUserService {
	@Autowired
	BusinessDetailsRepository businessDetailsRepository;

	@Autowired
	BusinessUserDetailsRepository businessUserDetailsRepository;
	
    //login check
	public BusinnessUserDetails validateUser(String username, String password) {
        // Check if username is email or mobile
        BusinnessUserDetails user;
        if (username.contains("@")) {
            user = BusinessUserDetailsRepository.findByEmailAndPassword(username, password);
        } else {
            try {
                Long mobile = Long.parseLong(username);
                user = BusinessUserDetailsRepository.findByMobileAndPassword(mobile, password);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return user;
    }
	//add user
	 public BusinnessUserDetails saveBusinessUserDetails(BusinnessUserDetails userDetails) {
	        return businessUserDetailsRepository.save(userDetails);
	    }

	 //add business details
	    @Transactional
	    public boolean saveBusinessDetails(Integer userId, BusinessDetails businessDetails) {
	        Optional<BusinnessUserDetails> user = businessUserDetailsRepository.findById(userId);
	        if (user.isPresent()) {
	            businessDetails.setUser(user.get());
	            return businessDetailsRepository.save(businessDetails) != null;
	        } else {
	            throw new RuntimeException("User not found with ID: " + userId);
	        }
	    }




//Fetch Business Details
	    public List<BusinessDetails> getAllBusinessDetails() {
	        return businessDetailsRepository.findAll();
	    }
	
	// Method to delete a BusinessUserDetails record along with its associated BusinessDetails
//    public void deleteBusinessUserDetailsById(Integer userId) {
//        if (businessUserDetailsRepository.existsById(userId)) {
//            businessUserDetailsRepository.deleteById(userId);
//        } else {
//            throw new RuntimeException("User with ID " + userId + " not found.");
//        }
//    }
//    
//    @Transactional
//    public BusinnessUserDetails updateBusinessUserDetails(Integer id, BusinnessUserDetails updatedDetails) {
//        // Check if the user exists
//        BusinnessUserDetails existingUser = businessUserDetailsRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//
//        // Update fields except the id
//        existingUser.setName(updatedDetails.getName());
//        existingUser.setEmail(updatedDetails.getEmail());
//        existingUser.setMobile(updatedDetails.getMobile());
//        existingUser.setPassword(updatedDetails.getPassword());
//        existingUser.setRole(updatedDetails.getRole());
//        //existingUser.setBusiness(updatedDetails.getBusiness()); // Optional, depending on your logic
//
//        // Save the updated user and return the result
//        return businessUserDetailsRepository.save(existingUser);
//    }
//    
//    public BusinessDetails updateBusinessDetails(Integer id, BusinessDetails businessDetails) {
//        Optional<BusinessDetails> existingBusinessDetails = businessDetailsRepository.findById(id);
//        
//        if (existingBusinessDetails.isPresent()) {
//            BusinessDetails updatedBusinessDetails = existingBusinessDetails.get();
//            updatedBusinessDetails.setBusiness_name(businessDetails.getBusiness_name());
//            updatedBusinessDetails.setState(businessDetails.getState());
//            updatedBusinessDetails.setCity(businessDetails.getCity());
//            updatedBusinessDetails.setSector(businessDetails.getSector());
//            updatedBusinessDetails.setSub_sector(businessDetails.getSub_sector());
//            updatedBusinessDetails.setNo_of_employees(businessDetails.getNo_of_employees());
//            
//            // Update user details (you may want to check if the user exists)
//            BusinnessUserDetails userDetails = businessUserDetailsRepository.findById(businessDetails.getUser().getId()).orElse(null);
//            if (userDetails != null) {
//                updatedBusinessDetails.setUser(userDetails);
//            }
//
//            return businessDetailsRepository.save(updatedBusinessDetails);
//        }
//
//        return null; // or throw an exception depending on your design
//    }
	    
	    public List<BusinnessUserDetails> getAllUsers() {
	        return businessUserDetailsRepository.findAll();
	    }

	    public BusinnessUserDetails getUserById(Integer id) {
	        return businessUserDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	    }

	    public void updateUser(BusinnessUserDetails user) {
	        businessUserDetailsRepository.save(user);
	    }

	    public void deleteUser(Integer id) {
	        businessUserDetailsRepository.deleteById(id);
	    }
	    
	    //fetch business owner details
	    public Optional<BusinnessUserDetails> getUserByIdd(Integer id) {
	        return businessUserDetailsRepository.findById(id);
	    }
	   
	    public Optional<BusinnessUserDetails> getUserByName(String name) {
	        return businessUserDetailsRepository.findByName(name);
	    }
	    
	    public List<BusinnessUserDetails> getUsersByRole(String role) {
	        return businessUserDetailsRepository.findByRole(role);
	    }
	    
	    public Optional<BusinnessUserDetails> getUserByEmail(String email) {
	        return businessUserDetailsRepository.findByEmail(email);
	    }
	    
	    
	    // fetch business details
	    public Optional<BusinessDetails> getById(Integer id) {
	        return businessDetailsRepository.findById(id);
	    }
	    
	    // Method to get BusinessDetails by businessname
	    public List<BusinessDetails> getByBusinessName(String businessname) {
	        return businessDetailsRepository.findByBusinessname(businessname);
	    }

	    // Method to get BusinessDetails by subsector
	    public List<BusinessDetails> getBySubsector(String subsector) {
	        return businessDetailsRepository.findBySubsector(subsector);
	    }



	    public List<BusinessDetails> getByState(String state) {
	        return businessDetailsRepository.findByState(state);
	    }

	    public List<BusinessDetails> getByCity(String city) {
	        return businessDetailsRepository.findByCity(city);
	    }

	    public List<BusinessDetails> getBySector(String sector) {
	        return businessDetailsRepository.findBySector(sector);
	    }

	    public Page<BusinessDetails> getPaginatedBusinessDetails(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return businessDetailsRepository.findAll(pageable);
	    }
 
}
