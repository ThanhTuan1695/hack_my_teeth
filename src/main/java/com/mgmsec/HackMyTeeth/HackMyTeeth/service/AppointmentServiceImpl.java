package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.util.List;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Appointment;
import com.mgmsec.HackMyTeeth.HackMyTeeth.dao.AppointmentRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository; 
	
	@Override
	public List<Appointment> findAll(){
		return appointmentRepository.findAllAppointment();
	
	}
}
