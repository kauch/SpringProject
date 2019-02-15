package com.netcracker.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.netcracker.model.AppUser;

public class AppUserMapper implements RowMapper<AppUser> {
	 
    public static final String BASE_SQL //
            = "Select u.User_Id, u.User_Name, u.User_Email, u.Encryted_Password From App_User u ";
 
    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long userId = rs.getLong("User_Id");
        String userName = rs.getString("User_Name");
        String userEmail = rs.getString("User_Email");
        String encrytedPassword = rs.getString("Encryted_Password");
 
        return new AppUser(userId, userName, userEmail, encrytedPassword);
    }
 
}
