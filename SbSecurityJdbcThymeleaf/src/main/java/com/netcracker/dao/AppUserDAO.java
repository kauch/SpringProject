package com.netcracker.dao;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
	
	private static Logger log = LogManager.getLogger(AppUserDAO.class);
	
	public void createNewUser(AppUser newUser) {
		 Object[] paramArray = new Object[] {newUser.getUserId(),
		 							  		 newUser.getUserName(),
		 							  		 EncrytedPasswordUtils.encrytePassword(newUser.getEncrytedPassword()), 1};
		 
		 String sql = "insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED) values (?, ?, ?, ?)";
		 try {
			 this.getJdbcTemplate().update(sql, paramArray);
		 } catch (DuplicateKeyException e) {
			 log.info(newUser.getUserName() + " is duplicate!");
	     }
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
