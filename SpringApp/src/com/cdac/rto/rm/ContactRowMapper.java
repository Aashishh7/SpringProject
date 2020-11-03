package com.cdac.rto.rm;

import  com.cdac.rto.domain.Details;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class ContactRowMapper implements RowMapper<Details>{
    @Override
    public Details mapRow(ResultSet rs, int i) throws SQLException {
    	Details c=new Details();
        c.setVehicleId(rs.getInt("vehicleId"));
        c.setUserId(rs.getInt("userId"));
        c.setName(rs.getString("name"));
        c.setEmail(rs.getString("email"));
        c.setAddress(rs.getString("address"));       
        c.setPhone(rs.getString("phone"));       
        c.setRemark(rs.getString("remark"));               
        return c;
    }
    
}
