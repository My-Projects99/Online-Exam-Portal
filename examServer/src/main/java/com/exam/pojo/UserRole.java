package com.exam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_role")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnore
    private Role role;
}

//package com.exam.pojo;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Table(name = "user_role")
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//public class UserRole {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long userRoleId;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JsonIgnore
////	@JoinColumn(name = "user_id")
//	private User user;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JsonIgnore
////	@JoinColumn(name = "role_id")
//	private Role role;
//}
