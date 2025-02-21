package com.example.security.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.Entity.TokenRequest;
import com.example.security.Service.SecurityCheckService;

@RestController
@RequestMapping("/secure")
public class SecurityCheckController {

    private final SecurityCheckService securityCheckService;

    public SecurityCheckController(SecurityCheckService securityCheckService) {
        this.securityCheckService = securityCheckService;
    }

    // ✅ Endpoint to Check Logged-in User Details
    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(securityCheckService.getUserDetails(userDetails.getUsername()));
    }

    // ✅ Endpoint to Check JWT Validity
    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestBody TokenRequest tokenRequest) {
        boolean isValid = securityCheckService.validateToken(tokenRequest.getToken());
        return isValid ? ResponseEntity.ok("Valid Token") : ResponseEntity.badRequest().body("Invalid Token");
    }
}

