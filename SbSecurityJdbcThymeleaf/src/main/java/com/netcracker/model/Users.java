package com.netcracker.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "delivery_schema", name = "users")
public class Users {
	@Id
	@GeneratedValue
	@Column(name = "user_id", updatable = false, nullable = false)
	private Long userId;

	@Column(name = "user_name", length = 36, nullable = false)
	private String userName;

	@Column(name = "encryted_password", length = 128, nullable = false)
	private String encrytedPassword;

	@Column(name = "enabled", length = 1, nullable = false)
	private boolean enabled;

	@OneToMany(targetEntity = Order.class)
	@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
	private List<Order> orders;

	@ManyToMany(targetEntity = Roles.class)
	@JoinTable(schema = "delivery_schema", name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	private Set<Roles> roles = new HashSet<>();

	public Users() {
		//
	}

	public Users(Long userId, String userName, String encrytedPassword, boolean enabled, Set<Roles> roles) {
		this.userId = userId;
		this.userName = userName;
		this.encrytedPassword = encrytedPassword;
		this.enabled = enabled;
		this.roles = roles;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
