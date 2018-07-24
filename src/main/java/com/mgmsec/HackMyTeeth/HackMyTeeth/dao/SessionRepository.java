package com.mgmsec.HackMyTeeth.HackMyTeeth.dao;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Statement;
import java.util.List;

@Repository
public class SessionRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;
    private EntityManager manager;
    public List<Session> findByID(int userID){
    	try {
    		List <Session> result= jdbcTemplate.query("SELECT * FROM session WHERE userID=?",
					(rs, rowNum) -> new Session(rs.getLong("userID"), rs.getString("sessCookie"),rs.getString("username"),rs.getInt("role")), Integer.toString(userID));
    		System.out.println("THE FUCKING RESULT IS:" + result);
    		
    		return result;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    public boolean createNewCookie(int userID,String username, int role, String cookieid) {
    	try {
    		List<Session> result = this.findByID(userID);
    		System.out.println(result.size());
    		if(result != null && (result.size() > 0)) {
    			try {
    				jdbcTemplate.update("UPDATE session SET role=?, sessCookie=? where userID=?;",Integer.toString(role),cookieid,Integer.toString(userID));
    				return true;
    			}
    			catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    		else {
    			try {
    				jdbcTemplate.update("INSERT INTO session(userID,sessCookie,username,role) values (?,?,?,?);",Integer.toString(userID),cookieid,username,Integer.toString(role));
    				return true;
    			}
    			catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    public boolean deleteCookiebyString(String cookieID) {
    	try {
    		jdbcTemplate.update("DELETE from session where sessCookie='?'",cookieID);
    		return true;
    	}catch(Exception e) {
    		
    	}
    	return false;
    }
    public List<Session> findBySession(String cookieID){
    	try {
    		List <Session> result= jdbcTemplate.query("SELECT * FROM session WHERE sessCookie=?",
					(rs, rowNum) -> new Session(rs.getLong("userID"), rs.getString("sessCookie"),rs.getString("username"),rs.getInt("role")), cookieID);
    		System.out.println(result);
    		
    		return result;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
