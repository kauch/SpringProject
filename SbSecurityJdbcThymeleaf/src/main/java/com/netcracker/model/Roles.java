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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(schema = "delivery_schema", name = "Roles",
	uniqueConstraints = {
        @UniqueConstraint(name = "ROLES_UK", columnNames = "role_id") })
public class Roles {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_gen")
	@SequenceGenerator(name = "delivery_gen", initialValue = 1, allocationSize = 1, schema = "delivery_schema")
    @Column(name = "role_id", nullable = false)
    private Long roleId;
 
    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users = new HashSet<>();
    
	public Roles() {
		//
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	
}
