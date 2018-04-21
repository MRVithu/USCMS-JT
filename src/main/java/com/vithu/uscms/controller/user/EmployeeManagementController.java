package com.vithu.uscms.controller.user;


import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vithu.uscms.entities.Employee;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.others.DataEncryption;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.others.ValueValidator;
import com.vithu.uscms.service.user.EmployeeManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

/**
 * @author S.Sugashan
 * @CreatedOn 19th December 2017
 * @Purpose Controller for Add/Edit/Delete/View Single/View All Users
 */

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class EmployeeManagementController {

	String responseResult = "";
	private DataEncryption deService = new DataEncryption();
	private EmployeeManagementService emService = new EmployeeManagementService();

	// ADD NEW USER
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestParam("token") String token, HttpServletRequest request) {
		Employee addedEmployee = new Employee();
		User addedUser = new User();
		
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);
		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_ADD_EMPLOYEE) != null) {
			
					if (ValueValidator.validateText(request.getParameter("name"), "Name").isStatus() == false) {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Name");
					}
					else if(ValueValidator.validateText(request.getParameter("email"), "Email").isStatus() == false){
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Email");
					}
					else {
					 addedUser.setName(request.getParameter("name"));
					 addedUser.setEmail(request.getParameter("email"));
					 addedUser.setPassword(deService.encrptyMe(request.getParameter("password")));
					 addedUser.setUserName(request.getParameter("userName"));
					 addedUser.setMobile(request.getParameter("mobile"));
					 addedEmployee.setAddedBy(currentUser.getEmployee().getUser().getId());
					 addedEmployee.setRoleId(Integer.parseInt(request.getParameter("role")));
					 addedEmployee.setNic(request.getParameter("nic"));
					 addedEmployee.setAddress(request.getParameter("address"));
					 addedEmployee.setContact(request.getParameter("contact"));
					 addedEmployee.setDob(request.getParameter("dob"));
					 addedEmployee.setRegionId(Integer.parseInt(request.getParameter("region")));
					 addedEmployee.setUser(addedUser);
					 returnResult = emService.addUser(addedEmployee);
					}
				}
				 else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
				returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}
		try {
			responseResult = JsonFormer.form(returnResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return responseResult;
	}

	// VIEW USERS
	@RequestMapping(value = "/employee")
	public String viewUser(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		
		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "");
		GenericResult mandatoryResult = new GenericResult(false, MessageConstant.MSG_FAILED, " ", " ");
		
		try {
//			if (currentUser == null) {
//				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
//			} else if (currentUser != null) {
//				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_EMPLOYEE) != null) {
						returnResult = emService.getAllUser();
//				} else {
//					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
//				}
//			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}
		
		mandatoryResult = emService.getAllRegion();
		model.addAttribute("region", mandatoryResult);
		
		mandatoryResult = emService.getAllRole();
		model.addAttribute("role", mandatoryResult);
		
		if(URLFormatter.MEDIA_JSON.equals(mediaType)){
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			responseResult = "jsonview";
		}
		else{
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("employees", returnResult);
			responseResult = "employee";
		}
		
		return responseResult;
	}


	// DELETE USER
	@RequestMapping(value = "/deleteEmployee/{id}")
	@ResponseBody
	public String deleteUser(@RequestParam("token") String token, HttpServletRequest request, Model model, @PathVariable String id) {
	
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);
		
		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_EMPLOYEE) != null) {
					if(ValueValidator.validateTextForNumbers(id, "EmployeeID").isStatus() == false){
						returnResult = new GenericResult(false, MessageConstant.MSG_NOT_NUMBER, "Employee ID");
					}
					returnResult = emService.deleteUser(Integer.parseInt(id));
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
			}
			
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}
		try {
			responseResult = JsonFormer.form(returnResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseResult;
	}

	// UPDATE EXISTING USER
	@RequestMapping("/updateEmployee")
	@ResponseBody
	public String updateUser(@RequestParam("token") String token, HttpServletRequest request) {

		Employee editedEmployee = new Employee();
		User editedUser = new User();
		
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);
		
		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_ADD_EMPLOYEE) != null) {
					GenericResult validateResult = ValueValidator.validateText(request.getParameter("id"), "ID");
					
					if (validateResult.isStatus() == false) {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, validateResult.getMessage());
					}
					else {
					 editedUser.setId(Integer.parseInt(request.getParameter("id")));
					 editedUser.setName(request.getParameter("name"));
					 editedUser.setPassword(deService.encrptyMe(request.getParameter("password")));
					 editedUser.setUserName(request.getParameter("userName"));
					 editedUser.setMobile(request.getParameter("mobile"));
					 editedUser.setEmail(request.getParameter("email"));
					 editedEmployee.setUser(editedUser);
					 editedEmployee.setId(Integer.parseInt(request.getParameter("eid")));
					 editedEmployee.setAddedBy(currentUser.getEmployee().getUser().getId());
					 editedEmployee.setRoleId(Integer.parseInt(request.getParameter("role")));
					 editedEmployee.setNic(request.getParameter("nic"));
					 editedEmployee.setAddress(request.getParameter("address"));
					 editedEmployee.setContact(request.getParameter("contact"));
					 editedEmployee.setDob(request.getParameter("dob"));
					 editedEmployee.setRegionId(Integer.parseInt(request.getParameter("region")));
					 returnResult = emService.updateUser(editedEmployee);
					}
				}
				 else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
				returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}
		try {
			responseResult = JsonFormer.form(returnResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseResult;
	}

}
