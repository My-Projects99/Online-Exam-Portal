package com.exam.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.RoleDao;
import com.exam.dao.UserDao;
import com.exam.pojo.User;
import com.exam.pojo.UserRole;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception {
		// TODO Auto-generated method stub
		User local=userDao.findByUserName(user.getUserName());
		if(local != null) {
			System.out.println("User Already Present !!");
			throw new Exception("User Already Present !!");
		}
		else {
			for(UserRole ur : userRole) {
				roleDao.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRole);
			local=userDao.save(user);
		}
		return local;
	}

	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
	}

	@Override
	public String userDeleted(Long userId) {
		// TODO Auto-generated method stub
		userDao.deleteById(userId);
		return "User Deleted";
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public User updateUser(Long userId, User userDetails) {
		// TODO Auto-generated method stub
		User existingUser = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

	    existingUser.setUserName(userDetails.getUserName());
	    existingUser.setFirstName(userDetails.getFirstName());
	    existingUser.setLastName(userDetails.getLastName());
	    existingUser.setEmail(userDetails.getEmail());
	    existingUser.setPhone(userDetails.getPhone());
	    existingUser.setPassword(userDetails.getPassword());
	    existingUser.setProfile(userDetails.getProfile());

	    return userDao.save(existingUser);
	}

}
