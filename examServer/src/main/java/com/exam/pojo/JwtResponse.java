package com.exam.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {

	String token;

	public JwtResponse(String token) {
		super();
		this.token = token;
	}
	
	
}
