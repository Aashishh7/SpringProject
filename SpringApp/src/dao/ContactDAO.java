package dao;

import domain.Details;
import java.util.List;


public interface ContactDAO {

    public void save(Details c);

    public void update(Details c);

    public void delete(Details c);

    public void delete(Integer VehicleId);

    public Details findById(Integer VehicleId);

    public List<Details> findAll();

    public List<Details> findByProperty(String propName, Object propValue);
}
