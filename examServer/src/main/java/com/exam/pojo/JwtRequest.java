package com.exam.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtRequest {

	String username;
	String password;
	

	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
