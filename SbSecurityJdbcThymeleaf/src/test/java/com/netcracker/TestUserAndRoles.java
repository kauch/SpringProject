package com.netcracker;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.netcracker.model.Roles;
import com.netcracker.model.Users;

public class TestUserAndRoles {

	@Test
	public void test() {
		Roles testRole1 = new Roles(1L, "ROLE_ADMIN");
		Roles testRole2 = new Roles(2L, "ROLE_USER");

		Set<Roles> userSet = new HashSet<>();
		userSet.add(testRole1);
		Set<Roles> adminSet = new HashSet<>();
		userSet.add(testRole2);

		Users testUser1 = new Users(1L, "userCustomer", "123", true, userSet);
		Users testUser2 = new Users(2L, "userAdmin", "123", true, adminSet);

	}

}
