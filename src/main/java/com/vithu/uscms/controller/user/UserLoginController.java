package com.vithu.uscms.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.others.DataEncryption;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.others.ValueValidator;
import com.vithu.uscms.service.user.UserLoginService;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;
/**
 * @author M.Vithusanth
 * @CreatedOn 202th April 2018
 * @Purpose Controller for users to login
 */

@Controller
public class UserLoginController {

	String responseResult = "";

	private UserLoginService ulService = new UserLoginService();
	private DataEncryption deService = new DataEncryption();
	// FOR JSP LOGIN REDIRECT
	@RequestMapping("/directlogin")
	public String login() {
		System.out.println(deService.encrptyMe("1234"));
		return "login";
	}

	// CALL LOGIN METHOD
	@ResponseBody
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, HttpSession sesssion) {
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "");
		User logger = new User();

		try {
			JSONObject user = new JSONObject(request.getParameter("data"));
			System.out.println("obj : " + user);
			// Validate userName
			if (ValueValidator.validateText(user.getString("userName"), "Name").isStatus() == false) {
				returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Name");
			} else if (ValueValidator.validateText(user.getString("password"), "Password").isStatus() == false) {
				returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Password");
			} else {
				logger.setUserName(user.getString("userName"));
				logger.setPassword(user.getString("password"));
				returnResult = ulService.dologin(logger);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}
		System.out.println();
		try {
			responseResult = JsonFormer.form(returnResult);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (returnResult.isStatus() == true) {
			sesssion.setAttribute("Token", ((CurrentUser) returnResult.getResult()).getToken());
			sesssion.setAttribute("USER-NAME", ((CurrentUser) returnResult.getResult()).getEmployee().getUser().getUserName());
			sesssion.setAttribute("NAME", ((CurrentUser) returnResult.getResult()).getEmployee().getUser().getName());
			sesssion.setAttribute("Register-Date", ((CurrentUser) returnResult.getResult()).getEmployee().getUser().getAddedOn());
		}
		return responseResult;
	}

	 // CALL LOGOUT METHOD
    @RequestMapping(value = "/doLogout")
    public String logout(HttpServletRequest request, Model model, @RequestParam("token") String token, HttpSession sesssion) {
    	 String mediaType = URLFormatter.getMediaType(request);
    	 GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "");
    	
    	CurrentUser currentUser = TokenManager.validateToken(token);
		try {
			if(currentUser != null) {
				sesssion.setAttribute("Token", "");
			returnResult = ulService.dologout(currentUser.getEmployee().getUser().getId());
			}
			else {
				returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "User Id is empty");
			}
		}
		 catch(Exception e) {
					e.printStackTrace();
					returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		 }
		 if(URLFormatter.MEDIA_JSON.equals(mediaType)){
				returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
				request.setAttribute("response", returnResult);
				responseResult = "jsonview";
			}
			else{
				returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
				model.addAttribute("logout", returnResult);
				responseResult = "login";
			}
		 return responseResult;
    }	

}