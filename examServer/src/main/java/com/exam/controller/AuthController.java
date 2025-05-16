package com.exam.controller;

import com.exam.pojo.JwtRequest;
import com.exam.pojo.JwtResponse;
import com.exam.pojo.User;
import com.exam.config.JwtUtil;
import com.exam.services.UserDetailServiceImpl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
        System.out.println("Inside Controller :"+userDetails);
        
        final String token = jwtUtil.generateToken(userDetails);
        System.out.println(token);
        
        return ResponseEntity.ok(new JwtResponse(token));
    }
    // return the details of current user 
    @GetMapping("/current-user")
    public User currentUser(Principal principal) {
    	return (User)this.userDetailService.loadUserByUsername(principal.getName());
    }
}
//@RestController
//@CrossOrigin("*")
//public class AuthController {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private AuthenticationProvider authenticationProvider;
//
//    @Autowired
//    private UserDetailServiceImpl userDetailsService;
//
//    @PostMapping("/generate-token")
//    public JwtResponse authenticate(@RequestBody JwtRequest request) throws Exception {
//        try {
//            authenticationProvider.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
//            );
//        } catch (Exception e) {
//            throw new Exception("Invalid Credentials", e);
//        }
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
//        String token = jwtUtil.generateToken(userDetails);
//
//        return new JwtResponse(token);
//    }
//}

//package com.exam.controller;
//
//import com.exam.pojo.JwtRequest;
//import com.exam.pojo.JwtResponse;
//import com.exam.config.JwtUtil;
//import com.exam.services.UserDetailServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin("*")
//public class AuthController {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private AuthenticationProvider authenticationProvider;
//
//    @Autowired
//    private UserDetailServiceImpl userDetailsService;
//
//    @PostMapping("/generate-token")
//    public JwtResponse authenticate(@RequestBody JwtRequest request) throws Exception {
//        try {
//            authenticationProvider.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
//            );
//        } catch (Exception e) {
//            throw new Exception("Invalid Credentials", e);
//        }
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
//        String token = jwtUtil.generateToken(userDetails);
//
//        return new JwtResponse(token);
//    }
//}
