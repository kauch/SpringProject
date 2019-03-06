package com.netcracker.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AppRoleDAO extends JdbcDaoSupport {

	private static Logger log = LogManager.getLogger(AppRoleDAO.class);

	public AppRoleDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public void addRolesForUser(Long userID, Long roleID) {
		Object[] paramArray = new Object[] { userID, roleID };
		String sql = "insert into user_role (USER_ID, ROLE_ID) values (?, ?)";
		try {
			this.getJdbcTemplate().update(sql, paramArray);
		} catch (DuplicateKeyException e) {
			log.info(getRoleNames(roleID) + " is duplicate!");
		}
	}

	public List<String> getRoleNames(Long userId) {
		String sql = "Select r.Role_Name " //
				+ " from User_Role ur, App_Role r " //
				+ " where ur.Role_Id = r.Role_Id and ur.User_Id = ? ";

		Object[] params = new Object[] { userId };
		return this.getJdbcTemplate().queryForList(sql, params, String.class);
	}
}
