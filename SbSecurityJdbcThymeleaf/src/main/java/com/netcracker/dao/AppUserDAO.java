package com.netcracker.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.mapper.AppUserMapper;
import com.netcracker.model.AppUser;
import com.netcracker.utils.EncrytedPasswordUtils;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {

	@Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	public void createNewUser(AppUser newUser) {
		 String sql = "INSERT INTO app_user (user_id, user_name, encryted_password, enabled) values (4, ?, ?, 1)";
		 this.getJdbcTemplate()
		 	.update(sql, new Object[] {
		 			newUser.getUserName(),
		 			EncrytedPasswordUtils.encrytePassword(newUser.getEncrytedPassword()
		 					)});
	}
	
    public AppUser findUserAccount(String userName) {
    	
        String sql = AppUserMapper.BASE_SQL + " where u.User_Name = ? ";
        Object[] params = new Object[] { userName };
        AppUserMapper mapper = new AppUserMapper();
        try {
        	return this.getJdbcTemplate().queryForObject(sql, params, mapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
