package com.exam.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.pojo.User;

public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String userName);

}
