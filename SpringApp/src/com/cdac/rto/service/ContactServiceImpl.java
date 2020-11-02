package com.cdac.rto.service;

import  com.cdac.rto.dao.BaseDAO;
import  com.cdac.rto.dao.ContactDAO;
import  com.cdac.rto.domain.Details;
import  com.cdac.rto.rm.ContactRowMapper;
import  com.cdac.rto.util.StringUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactServiceImpl extends BaseDAO implements ContactService{
    
    @Autowired
    private ContactDAO contactDAO;
    
    @Override    
    public void save(Details c) {
        contactDAO.save(c);
    }

    @Override
    public void update(Details c) {
        contactDAO.update(c);
    }

    @Override
    public void delete(Integer VehicleId) {
        contactDAO.delete(VehicleId);
    }

    @Override
    public void delete(Integer[] VehicleIds) {
        String ids = StringUtil.toCommaSeparatedString(VehicleIds);
        String sql = "DELETE FROM Details WHERE VehicleId IN("+ids+")";
        getJdbcTemplate().update(sql);
    }

    @Override
    public List<Details> findUserDetails(Integer userId) {
        return contactDAO.findByProperty("userId", userId);
    }

    @Override
    public List<Details> findUserDetails(Integer userId, String txt) {
        String sql = "SELECT VehicleId, userId, name, phone, email, address, remark FROM Details WHERE userId=? AND (name LIKE '%"+txt+"%' OR address LIKE '%"+txt+"%' OR phone LIKE '%"+txt+"%' OR email LIKE '%"+txt+"%' OR remark LIKE '%"+txt+"%')";
        return getJdbcTemplate().query(sql, new ContactRowMapper(),userId); 
    }

    @Override
    public Details findById(Integer VehicleId) {
        return contactDAO.findById(VehicleId);
    }
    
}
