package dao;

import domain.Details;
import rm.ContactRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class ContactDAOImpl extends BaseDAO implements ContactDAO {

    @Override
    public void save(Details c) {
        String sql = "INSERT INTO Details(userId, name, phone, email, address, remark) VALUES(:userId, :name, :phone, :email, :address, :remark)";
        Map m = new HashMap();
        m.put("userId", c.getUserId());
        m.put("name", c.getName());
        m.put("phone", c.getPhone());
        m.put("email", c.getEmail());
        m.put("address", c.getAddress());
        m.put("remark", c.getRemark());
        SqlParameterSource ps = new MapSqlParameterSource(m);
        KeyHolder kh = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(sql, ps, kh);
        c.setVehicleId(kh.getKey().intValue());
    }

    @Override
    public void update(Details c) {
        String sql = "UPDATE details SET name=:name, phone=:phone, email=:email, address=:address, remark=:remark WHERE VehicleId=:VehicleId";
        Map m = new HashMap();
        m.put("VehicleId", c.getVehicleId());
        m.put("name", c.getName());
        m.put("phone", c.getPhone());
        m.put("email", c.getEmail());
        m.put("address", c.getAddress());
        m.put("remark", c.getRemark());
        getNamedParameterJdbcTemplate().update(sql, m);
    }

    @Override
    public void delete(Details c) {
        this.delete(c.getVehicleId());
    }

    @Override
    public void delete(Integer VehicleId) {
        String sql = "DELETE FROM  Details WHERE VehicleId=?";
        getJdbcTemplate().update(sql, VehicleId);
    }

    @Override
    public Details findById(Integer VehicleId) {
        String sql = "SELECT VehicleId, userId, name, phone, email, address, remark FROM Details WHERE VehicleId=?";
        return getJdbcTemplate().queryForObject(sql, new ContactRowMapper(), VehicleId);
    }

    @Override
    public List<Details> findAll() {
        String sql = "SELECT VehicleId, userId, name, phone, email, address, remark FROM Details";
        return getJdbcTemplate().query(sql, new ContactRowMapper());
    }

    @Override
    public List<Details> findByProperty(String propName, Object propValue) {
        String sql = "SELECT VehicleId, userId, name, phone, email, address, remark FROM  Details WHERE "+propName+"=?";
        return getJdbcTemplate().query(sql, new ContactRowMapper(), propValue);
    }

}
