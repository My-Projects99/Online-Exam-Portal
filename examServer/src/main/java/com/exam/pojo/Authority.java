package com.exam.pojo;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private String authority;

    public Authority(String roleName) {
        this.authority = roleName;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}


//package com.exam.pojo;
//
//import org.springframework.security.core.GrantedAuthority;
//
//
//public class Authority implements GrantedAuthority{
//
//	private String authority;
//
//
//	public Authority(String roleName) {
//		// TODO Auto-generated constructor stub
//		this.authority = authority;
//	}
//
//
//	@Override
//	public String getAuthority() {
//		// TODO Auto-generated method stub
//		return this.authority;
//	}
//
//}
