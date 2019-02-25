package com.netcracker.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.netcracker.model.AppOrder;
import com.netcracker.model.AppUser;

public class AppOrderMapper implements RowMapper<AppOrder> {

    public static final String BASE_SQL
            = "Select * from App_Order";

    @Override
    public AppOrder mapRow(ResultSet rs, int rowNum)throws SQLException {
        Long ownerID = rs.getLong("Owner_ID");
        Long id = rs.getLong("Order_Id");
        int weight = rs.getInt("Weight");
        String dest = rs.getString("Destination_Point");
        
        return new AppOrder(ownerID, id, weight, dest);
    }
}
