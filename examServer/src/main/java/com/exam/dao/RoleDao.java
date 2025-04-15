package com.exam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.pojo.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

}
