package com.exam.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.dao.UserDao;
import com.exam.pojo.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<User> user = this.userDao.findByUsername(username);
        
        // Check if the user is not found (empty Optional)
        if (user.isEmpty()) {
            System.out.println("User Not Found !!");
            throw new UsernameNotFoundException("No User Found !!");
        }
        
        System.out.println("Loaded User: " + user.get());
        return user.get();  // Return the User object, unwrapped from the Optional
    }
}


//package com.exam.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.exam.dao.UserDao;
//import com.exam.pojo.User;
//
//@Service
//public class UserDetailServiceImpl  implements UserDetailsService{
//	
//	@Autowired
//	private UserDao userDao;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		System.out.println(username);
//		User user=this.userDao.findByUsername(username);
//		if(user==null) {
//			System.out.println("User Not Found !!");
//			throw new UsernameNotFoundException("No User Found !!");
//		}
//		System.out.println("Loaded User: " +user);
//		return user;
//	}
//
//}
