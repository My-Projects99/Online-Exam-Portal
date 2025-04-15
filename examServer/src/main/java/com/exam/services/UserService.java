package com.exam.services;

import java.util.List;
import java.util.Set;

import com.exam.pojo.User;
import com.exam.pojo.UserRole;

public interface UserService {

	//Create User
	public User createUser(User user,Set<UserRole> userRole) throws Exception;
	//Get Single user by UserName
	public User getUser(String userName);
	//Delete User By id
	public String userDeleted(Long userId);
	
	//Get All User 
	public List<User> getAllUsers();
    //Update User
	public User updateUser(Long userId, User userDetails);

}
