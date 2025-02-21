package com.Project.Dailymock.Controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Project.Dailymock.Entity.BusinessDetails;
import com.Project.Dailymock.Entity.BusinnessUserDetails;
import com.Project.Dailymock.Entity.Employee;
import com.Project.Dailymock.Repository.BusinessUserDetailsRepository;
import com.Project.Dailymock.Service.BusinessUserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/business")
public class BusinessUserController {

	@Autowired
	BusinessUserService businessUserService;

	@Autowired
	BusinessUserDetailsRepository businessUserDetailsRepository;

	// Home page
	@GetMapping("/home")
	@Operation
	public String home(Model model) {
		model.addAttribute("employee", new Employee());
		return "home";

	}

	@GetMapping("/login")
	@Operation
	public String login(Model model) {
			model.addAttribute("employee", new Employee()); // Adding the employee attribute to the model	
			return "login"; // Return the login view page
		}

	@PostMapping("/login")
	@Operation
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, Model model) {
		
			BusinnessUserDetails user = businessUserService.validateUser(username, password);

			if (user != null) {
				session.setAttribute("user", user);
				return "redirect:/api/business/home"; // Redirect to home or dashboard page
			} else {
				model.addAttribute("errorMessage", "Invalid username or password.");
				return "login";
			}
		}
	

	// Show Registration Page
	@GetMapping("/register")
	@Operation
	public String showRegistrationPage() {
		return "registeruser"; // Returning the registration page view
	}

	// Handle user registration
	@PostMapping("/registerUser")
	@Operation
	public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam Long mobile,
	        @RequestParam String password, @RequestParam String role, Model model) {
	    try {
	        // Create a new user object
	        BusinnessUserDetails userDetails = new BusinnessUserDetails();
	        userDetails.setName(name);
	        userDetails.setEmail(email);
	        userDetails.setMobile(mobile);
	        userDetails.setPassword(password);
	        userDetails.setRole(role);

	        // Save user and get the saved user with generated ID
	        BusinnessUserDetails savedUser = businessUserService.saveBusinessUserDetails(userDetails);

	        // Redirect to add business details page with userId as a query parameter
	        return "redirect:/api/business/addbusiness?userId=" + savedUser.getId();
	    } catch (Exception e) {
	        // Add error message to the model
	        model.addAttribute("error", "Error registering user: " + e.getMessage());
	        return "registeruser";
	    }
	}


	// Show add business details page
	@GetMapping("/addbusiness")
	@Operation
	public String showAddBusinessDetailsPage(@RequestParam Integer userId, Model model) {
		model.addAttribute("userId", userId);
		return "businessdetails";
	}

	// Save business details
	@PostMapping("/savebusiness")
	@Operation
	public String saveBusinessDetails(@RequestParam Integer userId, @ModelAttribute BusinessDetails businessDetails) {
	    try {
	        // Save business details
	        businessUserService.saveBusinessDetails(userId, businessDetails);
	        return "redirect:/api/business/home";
	    } catch (Exception e) {
	        return "businessdetails"; // Redirect back to the business details page in case of error
	    }
	}

	// fetch business user details
	@GetMapping("/userdetails")
	@Operation
	public String viewAllBusinessUsers(HttpServletRequest request) {
	    try {
	        // Fetch all users from the database
	        List<BusinnessUserDetails> userList = businessUserDetailsRepository.findAll();

	        // Set the user list as a request attribute
	        request.setAttribute("users", userList);

	        // Return the name of the JSP page
	        return "view-users";
	    } catch (Exception e) {
	        // In case of an error, set an error message in the request
	        request.setAttribute("errorMessage", 
	                "An error occurred while fetching user details. Please try again later.");
	        return "error"; // Redirect to an error page or return the error view name
	    }
	}


	// Fetch Business Details
	@GetMapping("/viewBusinessDetails")
	@Operation
	public String viewBusinessDetails(Model model) {
	    try {
	        // Fetch all business details from the service
	        List<BusinessDetails> businessDetails = businessUserService.getAllBusinessDetails();

	        // Add business details to the model
	        model.addAttribute("businessDetails", businessDetails);

	        // Return the view name to display the business details
	        return "viewBusiness"; // Ensure this view exists in your resources
	    } catch (Exception e) {
	        // Add an error message to the model in case of an exception
	        model.addAttribute("errorMessage",
	                "An error occurred while fetching business details. Please try again later.");
	        return "error"; // Redirect to an error page or show an error view
	    }
	}


	// update business user details

	@GetMapping("/list")
	@Operation
	public String listUsers(Model model) {
		model.addAttribute("users", businessUserService.getAllUsers());
		return "business-user-list";
	}

	@GetMapping("/edit/{id}")
	@Operation
	public String editUser(@PathVariable Integer id, Model model) {
		BusinnessUserDetails user = businessUserService.getUserById(id);
		model.addAttribute("user", user);
		return "edit-business-user";
	}

	@PostMapping("/update")
	@Operation
	public String updateUser(@ModelAttribute BusinnessUserDetails user) {
		businessUserService.updateUser(user);
		return "redirect:/api/business/list";
	}

	@GetMapping("/delete/{id}")
	@Operation
	public String deleteUser(@PathVariable Integer id) {
		businessUserService.deleteUser(id);
		return "redirect:/api/business/list";
	}

	// fetch business owner details by id
	@GetMapping("/{id}")
	@Operation
	public ResponseEntity<BusinnessUserDetails> getUserById(@PathVariable Integer id) {
		Optional<BusinnessUserDetails> userDetails = businessUserService.getUserByIdd(id);
		if (userDetails.isPresent()) {
			return ResponseEntity.ok(userDetails.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// fetch business owner details by name
	@GetMapping("/by-name/{name}")
	@Operation
	public ResponseEntity<BusinnessUserDetails> getUserByName(@PathVariable String name) {
		Optional<BusinnessUserDetails> userDetails = businessUserService.getUserByName(name);
		if (userDetails.isPresent()) {
			return ResponseEntity.ok(userDetails.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// fetch owner details by role
	@GetMapping("/by-role/{role}")
	@Operation
	public ResponseEntity<List<BusinnessUserDetails>> getUsersByRole(@PathVariable String role) {
		List<BusinnessUserDetails> userDetails = businessUserService.getUsersByRole(role);
		if (!userDetails.isEmpty()) {
			return ResponseEntity.ok(userDetails);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// fetch owner details by email
	@GetMapping("/by-email/{email}")
	@Operation
	public ResponseEntity<BusinnessUserDetails> getUserByEmail(@PathVariable String email) {
		Optional<BusinnessUserDetails> userDetails = businessUserService.getUserByEmail(email);
		if (userDetails.isPresent()) {
			return ResponseEntity.ok(userDetails.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// fetch business by id
	@GetMapping("/by-business/{id}")
	@Operation
	public ResponseEntity<BusinessDetails> getById(@PathVariable Integer id) {
		Optional<BusinessDetails> businessDetails = businessUserService.getById(id);
		if (businessDetails.isPresent()) {
			return ResponseEntity.ok(businessDetails.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// fetch by state
	@GetMapping("/by-state/{state}")
	@Operation
	public ResponseEntity<List<BusinessDetails>> getByState(@PathVariable String state) {
		List<BusinessDetails> details = businessUserService.getByState(state);
		return details.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(details);
	}

	// fetch by city
	@GetMapping("/by-city/{city}")
	@Operation
	public ResponseEntity<List<BusinessDetails>> getByCity(@PathVariable String city) {
		List<BusinessDetails> details = businessUserService.getByCity(city);
		return details.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(details);
	}

	// fetch by sector
	@GetMapping("/by-sector/{sector}")
	@Operation
	public ResponseEntity<List<BusinessDetails>> getBySector(@PathVariable String sector) {
		List<BusinessDetails> details = businessUserService.getBySector(sector);
		return details.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(details);
	}

	// Endpoint to fetch business details by businessname
	@GetMapping("/by-businessname/{businessname}")
	@Operation
	public ResponseEntity<List<BusinessDetails>> getBusinessByBusinessName(@PathVariable String businessname) {
		List<BusinessDetails> businessDetails = businessUserService.getByBusinessName(businessname);
		return ResponseEntity.ok(businessDetails);
	}

	// Endpoint to fetch business details by subsector
	@GetMapping("/by-subsector/{subsector}")
	@Operation
	public ResponseEntity<List<BusinessDetails>> getBusinessBySubsector(@PathVariable String subsector) {
		List<BusinessDetails> businessDetails = businessUserService.getBySubsector(subsector);
		return ResponseEntity.ok(businessDetails);
	}

	@GetMapping("/detail")
	public Page<BusinessDetails> getBusinessDetails(@RequestParam(defaultValue = "0") int page, // Default page 0
			@RequestParam(defaultValue = "10") int size) { // Default page size 10
		
		return businessUserService.getPaginatedBusinessDetails(page, size);
	}
}
