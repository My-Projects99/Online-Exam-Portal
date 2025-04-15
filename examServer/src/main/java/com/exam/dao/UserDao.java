package com.exam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.pojo.User;

public interface UserDao extends JpaRepository<User, Long> {

	public User findByUserName(String userName);

}
