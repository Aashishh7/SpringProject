package com.cdac.rto.service;

import  com.cdac.rto.domain.Details;
import java.util.List;


public interface ContactService {

    public void save(Details c);

    public void update(Details c);

    public void delete(Integer VehicleId);
   
    public void delete(Integer[] VehicleIds);
    
    public Details findById(Integer VehicleId);

    
    public List<Details> findUserDetails(Integer userId);

    
    public List<Details> findUserDetails(Integer userId, String txt);
}
