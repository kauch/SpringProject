package com.netcracker.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.netcracker.utils.EncrytedPasswordUtils;

@Entity
@Table(schema = "delivery_schema", name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_gen")
	@SequenceGenerator(name = "delivery_gen", initialValue = 1, allocationSize = 1, schema = "delivery_schema")
	@Column(name = "user_id", updatable = false, nullable = false, unique = true)
	private Long userId;

	@Column(name = "user_name", length = 36, nullable = false, unique = true)
	private String userName;

	@Column(name = "user_email", length = 128, nullable = false, unique = true)
	private String userEmail;

	@Column(name = "encryted_password", length = 128, nullable = false)
	private String encrytedPassword;

	@OneToMany(targetEntity = Order.class)
	@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
	private List<Order> orders;

	@ManyToMany(targetEntity = Roles.class, fetch = FetchType.EAGER)
	@JoinTable(schema = "delivery_schema", name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	private Set<Roles> roles = new HashSet<>();
	
	@ManyToMany(targetEntity = Address.class, fetch = FetchType.EAGER)
	@JoinTable(schema = "delivery_schema", name = "users_address", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id"))
	private Set<Address> address = new HashSet<>();

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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = EncrytedPasswordUtils.encrytePassword(encrytedPassword);
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

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

//	public Long uniqueID() {
//    	LocalDateTime id = LocalDateTime.now();
//    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("YYMMDDHHmmssSSS");
//        String dataNew = fmt.format(id);
//        Random myRandom = new Random();
//        StringBuilder bld = new StringBuilder();
//        bld.append(dataNew);
//        for(int i = 0; i < 3; i++){
//        	bld.append(myRandom.nextInt(9));
//        }
//        Long result = Long.valueOf(bld.toString());
//        log.info("id new user = " + result);
//        return result;
//    }

}
