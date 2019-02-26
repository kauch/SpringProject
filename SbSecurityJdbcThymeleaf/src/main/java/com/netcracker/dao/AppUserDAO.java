package com.netcracker.dao;

import java.util.List;

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
	
	private static Logger log = LogManager.getLogger(AppUserDAO.class);
	
	@Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	public boolean createNewUser(AppUser newUser) {
		boolean status = false;
		Object[] paramArray = new Object[] {newUser.getUserId(),
		 							  		newUser.getUserName(),
		 							  		newUser.getUserEmail(),
		 							  		EncrytedPasswordUtils.encrytePassword(newUser.getEncrytedPassword()), 1};
		 
		String sql = "insert into App_User (USER_ID, USER_NAME, USER_EMAIL, ENCRYTED_PASSWORD, ENABLED) values (?, ?, ?, ?, ?)";
		try {
			this.getJdbcTemplate().update(sql, paramArray);
			status = true;
		} catch (DuplicateKeyException e) {
			log.info(newUser.getUserName() + " is duplicate!");
	    }
		return status;
	}
	
	public List<AppUser> getAll(){

	    try {
	    	return this.getJdbcTemplate().query(AppUserMapper.BASE_SQL, new AppUserMapper());
	    }catch (EmptyResultDataAccessException e ){
	    	log.info("There's some problems with getting list of user");
        }
		return null;
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
