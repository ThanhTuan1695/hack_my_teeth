package com.mgmsec.HackMyTeeth.HackMyTeeth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mgmsec.HackMyTeeth.HackMyTeeth.service.UserService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{
	@Autowired
	private UserService userService;
	
	@Autowired
	SecuritySettings securitySettings;
	
  /**
   * This event is executed as late as conceivably possible to indicate that 
   * the application is ready to service requests.
   */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		switch (securitySettings.getResetPassword()) {
		case True:
			System.out.println("WEIJRPLKWJERKJWER-erthkertjherwerkter\n\n\n\n");
			userService.resetAllPassword();
			break;
		case False:
			//System.out.println("NO EXECUTE-erthkertjherwerkter\n\n\n\n");

			break;
		default:
			//System.out.println("NO EXECUTE DEFAULT-erthkertjherwerkter\n\n\n\n");

			break;
	}
	

	}
}
