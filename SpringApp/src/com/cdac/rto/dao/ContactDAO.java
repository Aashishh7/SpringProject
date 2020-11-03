package com.cdac.rto.dao;

import  com.cdac.rto.domain.Details;
import java.util.List;


public interface ContactDAO {

    public void save(Details c);

    public void update(Details c);

    public void delete(Details c);

    public void delete(Integer vehicleId);

    public Details findById(Integer vehicleId);

    public List<Details> findAll();

    public List<Details> findByProperty(String propName, Object propValue);
}
