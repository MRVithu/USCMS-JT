package com.vithu.uscms.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.service.user.CustomerManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose  Controller for Add/Edit/Delete/View Single/View All Customers
 */

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class CustomerManagementController {
	private CustomerManagementService cusService = new CustomerManagementService();

	private String response;

	// VIEW CUSTOMER
	@RequestMapping("/customer")
	public String viewCustomer(@RequestParam("token") String token, HttpServletRequest request, Model model) {
//		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "","","","");
		
		try {
//			if (currentUser == null) {
//				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
//			} else if (currentUser != null) {
//				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {
			
						returnResult = cusService.getAllCustomers();
//				} else {
//					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
//				}
//			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}
		
		if(URLFormatter.MEDIA_JSON.equals(mediaType))
		{
			System.out.println("hi json");
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			response = "jsonview";
			System.out.println("**************"+response);
		}
		else
		{
			System.out.println("hi not json");
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("customers", returnResult);
			response = "customer";
		}
		return response;
	}

	// DISABLE CUSTOMER
	@RequestMapping("/disableCustomer")
	@ResponseBody
	public String disableCustomer(@RequestParam("token") String token, @RequestParam("id") String id) {
		
		  CurrentUser currentUser = TokenManager.validateToken( token ); 
		  if (  currentUser.getAuthorityMap().get( AuthorityConstant.AUTH_VIEW_CUSTOMER ) != null ) {		 
			try {
				response = JsonFormer.form(cusService.deleteCustomer(id));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  return response;
	}

//	// ADD CUSTOMER
//	@RequestMapping("/addCustomer")
//	@ResponseBody
//	public String addCustomer(@RequestParam("token") String token, @RequestParam("name") String name,
//			@RequestParam("email") String email, @RequestParam("mobile") String mobile,
//			@RequestParam("userName") String userName, @RequestParam("password") String password) {
//		
//		  CurrentUser currentUser = TokenManager.validateToken( token ); if (
//		  currentUser.getAuthorityMap().get( AuthorityConstant.AUTH_VIEW_CUSTOMER ) !=
//		  null ) {
//		 
//		GenericResult result = new GenericResult(true, "", "");
//		User addedUser = new User();
//		Customer newCustomer = new Customer();
//
//		// Name
//		result = ValueValidator.validateText(name, "Name");
//		if (result.isStatus()) {
//			addedUser.setName(name);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		// Email
//		result = ValueValidator.validateText(email, "Email");
//		if (result.isStatus()) {
//			addedUser.setEmail(email);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		// Mobile
//		result = ValueValidator.validateText(mobile, "mobile");
//		if (result.isStatus()) {
//			addedUser.setMobile(mobile);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		// User name
//		result = ValueValidator.validateText(userName, "userName");
//		if (result.isStatus()) {
//			addedUser.setUserName(userName);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		// Password
//		result = ValueValidator.validateText(password, "password");
//		if (result.isStatus()) {
//			addedUser.setPassword(password);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		newCustomer.setUser(addedUser);
//		try {
//			return JsonFormer.form(cusService.addCustomer(newCustomer));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "";
//		}
//
//		
//		  }
//		  else {
//			  try { response = JsonFormer.form( new GenericResult( false,
//		  MessageConstant.MSG_NO_AUTH, "" ) ); }
//		  catch ( JSONException e ) { 
//			  // TODO  Auto-generated catch block
//			  e.printStackTrace(); } 
//		  }
//		 
//	}
//
//	// Edit customer
//	@RequestMapping("/editCustomer")
//	@ResponseBody
//	public String editUser(@RequestParam("token") String token, @RequestParam("id") String id,
//			@RequestParam("name") String name, @RequestParam("email") String email,
//			@RequestParam("mobile") String mobile, @RequestParam("userName") String userName,
//			@RequestParam("password") String password) {
//		
//		  CurrentUser currentUser = TokenManager.validateToken( token ); if (
//		  currentUser.getAuthorityMap().get( AuthorityConstant.AUTH_VIEW_CUSTOMER ) !=
//		  null ) {
//		 
//		GenericResult result = new GenericResult(true, "", "");
//		User addedUser = new User();
//		Customer newCustomer = new Customer();
//
//		// Name
//		result = ValueValidator.validateText(name, "Name");
//		if (result.isStatus()) {
//			addedUser.setName(name);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		// Email
//		result = ValueValidator.validateText(email, "Email");
//		if (result.isStatus()) {
//			addedUser.setEmail(email);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		// Mobile
//		result = ValueValidator.validateText(mobile, "mobile");
//		if (result.isStatus()) {
//			addedUser.setMobile(mobile);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		// User name
//		result = ValueValidator.validateText(userName, "userName");
//		if (result.isStatus()) {
//			addedUser.setName(userName);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		// Password
//		result = ValueValidator.validateText(password, "password");
//		if (result.isStatus()) {
//			addedUser.setPassword(password);
//		} else {
//			try {
//				return JsonFormer.form(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		newCustomer.setUser(addedUser);
//		try {
//			return JsonFormer.form(cusService.editCustomer(id, newCustomer));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "";
//		}
//
//		
//		  } else { try { response = JsonFormer.form( new GenericResult( false,
//		  MessageConstant.MSG_NO_AUTH, "" ) ); } catch ( JSONException e ) { // TODO
//		  Auto-generated catch block e.printStackTrace(); } }
//		 
//	}
//
//	// Single view
//	@RequestMapping("/singleViewCustomer")
//	@ResponseBody
//	public String singleView(@RequestParam("token") String token, @RequestParam("id") int id) {
//		/*
//		 * CurrentUser currentUser = TokenManager.validateToken( token ); if (
//		 * currentUser.getAuthorityMap().get( AuthorityConstant.AUTH_VIEW_CUSTOMER ) !=
//		 * null ) {
//		 */
//
//		try {
//			response = JsonFormer.form(cusService.getSingleCustomer(id));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		/*
//		 * } else { // res=JsonFormer.form( new
//		 * GenericResult(false,MessageConstant.MSG_NO_AUTH,"") ); }
//		 */
//		return response;
//	}
}
