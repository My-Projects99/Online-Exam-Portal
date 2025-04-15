package com.exam.pojo;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phone;
	private boolean enable=true;
	private String profile;
	
	//user have many roles
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRoles=new HashSet<>();
	
	
	
	
	
	public User(Long id, String userName, String firstName, String lastName, String password, String email,
			String phone, String profile) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phone = phone;
//		this.about = about;
		this.profile = profile;
			
	}
	
	
}
