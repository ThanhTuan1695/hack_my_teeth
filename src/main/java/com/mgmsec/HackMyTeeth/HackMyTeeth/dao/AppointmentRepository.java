package com.mgmsec.HackMyTeeth.HackMyTeeth.dao;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Statement;
import java.util.List;

@Repository
public class AppointmentRepository {
	@Autowired
	    private JdbcTemplate jdbcTemplate;
	    private EntityManager manager; 
	    public List<Appointment> findAllAppointment(){
	    	try {
	    		List<Appointment> result = jdbcTemplate.query("SELECT * from user", (rs, rowNum) ->  new Appointment(rs.getInt("appID"),rs.getString("title"),rs.getString("time"),rs.getInt("cusID"),rs.getInt("denID"),rs.getString("description")));
	    		return result;
	    	}
	    	catch(Exception e) {
	    		e.getStackTrace();
	    		return null;
	    	}
	    }
}
