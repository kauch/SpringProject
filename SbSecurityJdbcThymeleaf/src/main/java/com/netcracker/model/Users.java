package com.netcracker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(schema = "delivery_schema", name = "Users",
	uniqueConstraints = {
        @UniqueConstraint(name = "USERS_UK", columnNames = "user_name")})
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_gen")
	@SequenceGenerator(name = "delivery_gen", initialValue = 1, allocationSize = 1, schema = "delivery_schema")
	@Column(name = "user_id", updatable = false, nullable = false)
	private Long userId;
	 
	@Column(name = "user_name", length = 36, nullable = false)
	private String userName;
	 
	@Column(name = "encryted_password", length = 128, nullable = false)
	private String encrytedPassword;
	 
	@Column(name = "enabled", length = 1, nullable = false)
	private boolean enabled;

	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "Users_Roles", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id")})
    private Set<Roles> roles = new HashSet<>();
	
	public Users() {
		//
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = encrytedPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
}
