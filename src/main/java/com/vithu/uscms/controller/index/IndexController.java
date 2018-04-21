package com.vithu.uscms.controller.index;


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author S.Sugashan
 * @CreatedOn 19th January 2018
 * @Purpose Controller for Initial
 */

@Controller
public class IndexController {

	@ResponseBody
	@RequestMapping("/")
	public String hello(HttpServletRequest request) {
	    return "hello";
	}
	

	@RequestMapping("vehicles/vehicle")
	public String vehicle() {
	    return "vehicle";
	}
	
}
