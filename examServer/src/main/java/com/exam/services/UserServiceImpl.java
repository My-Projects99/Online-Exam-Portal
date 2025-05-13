package com.exam.services;

import com.exam.dao.RoleDao;
import com.exam.dao.UserDao;
import com.exam.pojo.User;
import com.exam.pojo.UserRole;
import com.exam.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User createUser(User user, Set<UserRole> userRole) throws Exception {
        Optional<User> local = userDao.findByUsername(user.getUsername());
        if (local.isPresent()) {
            throw new Exception("User Already Present !!");
        } else {
            // Ensure the roles are valid before saving
            for (UserRole ur : userRole) {
                Role role = ur.getRole();
                if (role != null && role.getRoleId() != null) {
                    // Ensure the role exists in the database
                    Role existingRole = roleDao.findById(role.getRoleId())
                            .orElseThrow(() -> new RuntimeException("Role not found"));
                    ur.setRole(existingRole);  // Set the valid role
                }
            }

            // Add user roles
            user.getUserRoles().addAll(userRole);
            return userDao.save(user); // Save the user and associated roles
        }
    }

    @Override
    public User getUser(String userName) {
        return userDao.findByUsername(userName).orElse(null);
    }

    @Override
    public String userDeleted(Long userId) {
        userDao.deleteById(userId);
        return "User Deleted";
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        User existingUser = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(userDetails.getUsername());
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPhone(userDetails.getPhone());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setProfile(userDetails.getProfile());

        return userDao.save(existingUser);
    }
}


//package com.exam.services;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.exam.dao.RoleDao;
//import com.exam.dao.UserDao;
//import com.exam.pojo.Role;
//import com.exam.pojo.User;
//import com.exam.pojo.UserRole;
//
// Custom exception for user already exists
//class UserAlreadyExistsException extends Exception {
//    public UserAlreadyExistsException(String message) {
//        super(message);
//    }
//}
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private RoleDao roleDao;
//
//    @Override
//    public User createUser(User user, Set<UserRole> userRole) throws Exception {
//        // Check if the user already exists
//        Optional<User> existingUser = userDao.findByUsername(user.getUsername());
//        if (existingUser.isPresent()) {
//            System.out.println("User Already Present !!");
//            throw new UserAlreadyExistsException("User Already Present !!");
//        } else {
//            // Save roles if they are not already in the database
//            for (UserRole ur : userRole) {
//                Optional<Role> existingRole = roleDao.findById(ur.getRole().getRoleId());
//                if (!existingRole.isPresent()) {
//                    roleDao.save(ur.getRole());  // Save the role if it doesn't exist
//                }
//            }
//
//            // Add roles to the user
//            user.getUserRoles().addAll(userRole);
//
//            // Save the user to the database
//            return userDao.save(user);
//        }
//    }
//
//    @Override
//    public User getUser(String userName) {
//        // Get a user by their username
//    	Optional<User> local=userDao.findByUsername(userName);
//        return local.get();
//    }
//
//    @Override
//    public String userDeleted(Long userId) {
//        // Delete a user by their ID
//        userDao.deleteById(userId);
//        return "User Deleted";
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        // Get all users from the database
//        return userDao.findAll();
//    }
//
//    @Override
//    public User updateUser(Long userId, User userDetails) {
//        // Update user details by their ID
//        User existingUser = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//        existingUser.setUsername(userDetails.getUsername());
//        existingUser.setFirstName(userDetails.getFirstName());
//        existingUser.setLastName(userDetails.getLastName());
//        existingUser.setEmail(userDetails.getEmail());
//        existingUser.setPhone(userDetails.getPhone());
//        existingUser.setPassword(userDetails.getPassword());
//        existingUser.setProfile(userDetails.getProfile());
//
//        return userDao.save(existingUser);  // Save the updated user
//    }
//}


//package com.exam.services;
//
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.exam.dao.RoleDao;
//import com.exam.dao.UserDao;
//import com.exam.pojo.User;
//import com.exam.pojo.UserRole;
//
//@Service
//public class UserServiceImpl implements UserService {
//	
//	@Autowired
//	private UserDao userDao;
//	@Autowired
//	private RoleDao roleDao;
//
//	@Override
//	public User createUser(User user, Set<UserRole> userRole) throws Exception {
//		// TODO Auto-generated method stub
//		User local=userDao.findByUsername(user.getUsername());
//		if(local != null) {
//			System.out.println("User Already Present !!");
//			throw new Exception("User Already Present !!");
//		}
//		else {
//			for(UserRole ur : userRole) {
//				roleDao.save(ur.getRole());
//			}
//			user.getUserRoles().addAll(userRole);
//			local=userDao.save(user);
//		}
//		return local;
//	}
//
//	@Override
//	public User getUser(String userName) {
//		// TODO Auto-generated method stub
//		return userDao.findByUsername(userName);
//	}
//
//	@Override
//	public String userDeleted(Long userId) {
//		// TODO Auto-generated method stub
//		userDao.deleteById(userId);
//		return "User Deleted";
//	}
//
//	@Override
//	public List<User> getAllUsers() {
//		// TODO Auto-generated method stub
//		return userDao.findAll();
//	}
//
//	@Override
//	public User updateUser(Long userId, User userDetails) {
//		// TODO Auto-generated method stub
//		User existingUser = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//	    existingUser.setUsername(userDetails.getUsername());
//	    existingUser.setFirstName(userDetails.getFirstName());
//	    existingUser.setLastName(userDetails.getLastName());
//	    existingUser.setEmail(userDetails.getEmail());
//	    existingUser.setPhone(userDetails.getPhone());
//	    existingUser.setPassword(userDetails.getPassword());
//	    existingUser.setProfile(userDetails.getProfile());
//
//	    return userDao.save(existingUser);
//	}
//
//}
