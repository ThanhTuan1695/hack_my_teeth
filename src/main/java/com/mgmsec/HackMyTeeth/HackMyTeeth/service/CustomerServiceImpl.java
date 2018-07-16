package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.util.List;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Customer;
import com.mgmsec.HackMyTeeth.HackMyTeeth.dao.CustomerRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class CustomerServiceImpl  implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.displayAll();
	}

}
