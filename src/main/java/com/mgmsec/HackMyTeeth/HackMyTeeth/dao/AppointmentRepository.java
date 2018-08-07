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
	    public List<Appointment> findAll(String username){
	    	try {
	    		List<Appointment> result = jdbcTemplate.query("SELECT a.appID,a.title,a.time, CONCAT(u.firstName,' ',u.lastName) as cusName,CONCAT(ut.firstName,' ',ut.lastName) as denName,a.description FROM appointment a JOIN user u JOIN user ut ON a.cusID = u.userID AND a.denID = ut.userID AND a.denID= (SELECT userID FROM user WHERE username='"+username+"')", 
	    				(rs, rowNum) ->  new Appointment(rs.getInt("appID"),rs.getString("title"),rs.getString("time"),rs.getString("cusName"),rs.getString("denName"),rs.getString("description")));
	    		System.out.print(result);
	    		return result;
	    	}
	    	catch(Exception e) {
	    		e.getStackTrace();
	    		return null;
	    	}
	    }
	    public Boolean addAppointment(Appointment appointment){
	    	try{

	    		String sql  = "INSERT INTO appointment values(?,?,?,?,?,?)";
				jdbcTemplate.update(sql, null, appointment.getTitle(),appointment.getTime(), appointment.getDescription(), appointment.getCusID(), appointment.getDenID());
			} catch (Exception e){
	    		e.printStackTrace();
	    		return false;
			}
	    	return true;
		}
}
