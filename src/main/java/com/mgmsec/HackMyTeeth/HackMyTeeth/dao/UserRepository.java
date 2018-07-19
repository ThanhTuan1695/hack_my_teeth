package com.mgmsec.HackMyTeeth.HackMyTeeth.dao;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<User> findAll(){
		try {
			List<User> result = jdbcTemplate.query("SELECT userID,firstName,lastName,email from user",(rs,rowNum) -> new User(rs.getLong("userID"),rs.getString("username"),rs.getString("password"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email")));

			return result;
		}catch (Exception e) {
			e.getStackTrace();
		}
		return null;
		
	}
	
	public List<User> findByUsername(String username) {
		try {
			List<User> result = jdbcTemplate.query( "SELECT * FROM Users WHERE username=?", 
					   (rs, rowNum) -> new User(rs.getLong("userID"),rs.getString("username"),rs.getString("password"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email")),
					   				   username
					 );

			return result;
		} catch(Exception e) {
			e.printStackTrace();
		}
    return null;
}
	
}
