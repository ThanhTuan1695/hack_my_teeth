package com.mgmsec.HackMyTeeth.HackMyTeeth.dao;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CustomerRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<Customer> displayAll(){
		try {
			List<Customer> result = jdbcTemplate.query("SELECT * from customer",(rs,rowNum) -> new Customer(rs.getLong("cusID"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getString("phone"),rs.getString("gender"),rs.getLong("accountID")));
			System.out.println(result.toString());
			return result;
		}catch (Exception e) {
			e.getStackTrace();
		}
		return null;
		
	}
	
}
