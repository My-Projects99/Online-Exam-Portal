package com.exam.controller;

import com.exam.pojo.Role;
import com.exam.pojo.User;
import com.exam.pojo.UserRole;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Create User, register User
    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        user.setProfile("default.png");

        // Encoding Password with bcryptPasswordEncoder => Inject dependency @Autowire BcryptPasswordEncoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        // Assign default role
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(11L);  // Ensure this role exists in the DB
        role.setRoleName("STUDENT");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        try {
            // Create the user with role
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user, roles));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Already Present !!");
        }
    }

    // Get all users
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getAllUsers());
    }

    // Get user by username
    @GetMapping("/{userName}")
    public ResponseEntity<?> getUser(@PathVariable("userName") String userName) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUser(userName));
    }

    // Delete user by userId
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.userDeleted(userId));
    }

    // Update user by userId
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(userId, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
        }
    }
}


//package com.exam.controller;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.exam.pojo.Role;
//import com.exam.pojo.User;
//import com.exam.pojo.UserRole;
//import com.exam.services.UserService;
//
//@RestController
//@RequestMapping("/users")
//@CrossOrigin("*")
//public class UserController {
//	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	
//	//create User, register User
//	
//	@PostMapping("/")
//	public ResponseEntity<?> createUser(@RequestBody User user){
//		System.out.println(user);
//		
//		user.setProfile("default.png");
//		
//		//Encoding Password with bcryptPasswordEncoder =>Inject dependency @Autowire BcryptPasswordEncoder
//		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
//		
//		
//		Set<UserRole> roles=new HashSet<>();
//		
//		Role role=new Role();
//		role.setRoleId(11L);
//		role.setRoleName("STUDENT");
//		
//		UserRole userrole=new UserRole();
//		userrole.setUser(user);
//		userrole.setRole(role);
//		
//		roles.add(userrole);
//		try {
//			return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user, roles));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Already Present !!");
//		}
//		
//	}
//	// All Users List
//	
//	@GetMapping
//	public ResponseEntity<?> getAllUsers(){
//		return ResponseEntity.status(HttpStatus.FOUND).body(userService.getAllUsers());
//	}
//	//User Find by UserName.
//	
//	@GetMapping("/{userName}")
//	public ResponseEntity<?> GetUser(@PathVariable("userName") String userName){
//		System.out.println(userName);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUser(userName));	
//	}
//	
//	//Delete User By UserId
//	
//	@DeleteMapping("/{userId}")
//	public ResponseEntity<?> DeleteUser(@PathVariable("userId") Long userId){
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.userDeleted(userId));
//	}
//	
//	//Update User By UserId
//	
//	@PutMapping("/{userId}")
//	public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody User userDetails) {
//	    try {
//	        User updatedUser = userService.updateUser(userId, userDetails);
//	        return ResponseEntity.ok(updatedUser);
//	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
//	    }
//	}
//
//	
//
//}
