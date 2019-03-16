package com.netcracker.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema = "delivery_schema", name = "roles")
public class Roles  implements Serializable {

	private static final long serialVersionUID = 2791130208399609774L;

	@Id
	@GeneratedValue
	@Column(name = "role_id", nullable = false)
	private Long roleId;

	@Column(name = "role_name", length = 30, nullable = false)
	private String roleName;

	@ManyToMany(targetEntity = Users.class, mappedBy = "roles")
	private Set<Users> users = new HashSet<>();

}
