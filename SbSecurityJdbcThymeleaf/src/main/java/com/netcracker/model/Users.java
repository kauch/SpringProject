package com.netcracker.model;

import java.io.Serializable;
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
public class Users implements Serializable {

	private static final long serialVersionUID = -634944125467394660L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_gen_user")
	@SequenceGenerator(name = "delivery_gen_user", initialValue = 1, allocationSize = 1, schema = "delivery_schema")
	@Column(name = "user_id", updatable = false, nullable = false, unique = true)
	private Long userId;

	@Column(name = "user_login", length = 36, nullable = false, unique = true)
	private String login;

	@Column(name = "first_name", length = 36, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 36, nullable = false)
	private String lastName;

	@Column(name = "gender", length = 5, nullable = false)
	private boolean gender;

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
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = EncrytedPasswordUtils.encrytePassword(encrytedPassword);
	}

	public String getTextGender(boolean gender) {
		return (gender) ? "Male" : "Female";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (encrytedPassword == null) {
			if (other.encrytedPassword != null)
				return false;
		} else if (!encrytedPassword.equals(other.encrytedPassword))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
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
