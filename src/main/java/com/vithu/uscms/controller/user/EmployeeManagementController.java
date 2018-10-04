package com.vithu.uscms.controller.user;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
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
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Controller for Add/Edit/Delete/View Single/View All Employees
 */

@Controller
public class EmployeeManagementController {

	String responseResult = "";
	private DataEncryption deService = new DataEncryption();
	private EmployeeManagementService emService = new EmployeeManagementService();

	// ADD NEW USER
	@CrossOrigin
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
					JSONObject emp = new JSONObject(request.getParameter("data"));

					if (ValueValidator.validateText(emp.getString("userName"), "User Name").isStatus() == false) {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Name");
					} else if (ValueValidator.validateText(emp.getString("email"), "Email").isStatus() == false) {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Email");
					} else {
						addedUser.setName(emp.getString("name"));
						addedUser.setEmail(emp.getString("email"));
						addedUser.setPassword(deService.encrptyMe(emp.getString("password")));
						addedUser.setUserName(emp.getString("userName"));
						addedUser.setMobile(emp.getString("mobile"));
						addedEmployee.setAddedBy(currentUser.getEmployee().getUser().getId());
						addedEmployee.setRoleId(Integer.parseInt(emp.getString("role")));
						addedEmployee.setNic(emp.getString("nic"));
						addedEmployee.setAddress(emp.getString("address"));
						addedEmployee.setContact(emp.getString("contact"));
						addedEmployee.setDob(emp.getString("dob"));
						addedEmployee.setUser(addedUser);
						returnResult = emService.addUser(addedEmployee);
					}
				} else {
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

	// VIEW EMPLOYEE
	@RequestMapping(value = "/employee")
	public String viewUser(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		DataEncryption de = new DataEncryption();

		System.out.println(de.encrptyMe("mrvithu") + "------------");
		// CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "");
		GenericResult mandatoryResult = new GenericResult(false, MessageConstant.MSG_FAILED, " ", " ");

		try {
			// if (currentUser == null) {
			// returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN,
			// "");
			// } else if (currentUser != null) {
			// if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_EMPLOYEE)
			// != null) {
			returnResult = emService.getAllUser();
			// } else {
			// returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
			// }
			// }
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}

		mandatoryResult = emService.getAllRole();
		model.addAttribute("role", mandatoryResult);

		if (URLFormatter.MEDIA_JSON.equals(mediaType)) {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			responseResult = "jsonview";
		} else {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("employees", returnResult);
			responseResult = "employee";
		}

		return responseResult;
	}

	// DELETE EMPLOYEE
	@CrossOrigin
	@RequestMapping(value = "/deleteEmployee/{id}")
	@ResponseBody
	public String deleteUser(@RequestParam("token") String token, HttpServletRequest request, Model model,
			@PathVariable String id) {

		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_EMPLOYEE) != null) {
					if (ValueValidator.validateTextForNumbers(id, "EmployeeID").isStatus() == false) {
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

	// UPDATE EXISTING EMPLOYEE
	@CrossOrigin
	@RequestMapping("/updateEmployee")
	@ResponseBody
	public String updateEmployee(@RequestParam("token") String token, HttpServletRequest request) {

		Employee editedEmployee = new Employee();
		User editedUser = new User();

		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_ADD_EMPLOYEE) != null) {
					JSONObject emp = new JSONObject(request.getParameter("data"));
					GenericResult validateResult = ValueValidator.validateText(emp.getString("uid"), "ID");

					if (validateResult.isStatus() == false) {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, validateResult.getMessage());
					} else {
						editedUser.setId(Integer.parseInt(emp.getString("uid")));
						editedUser.setName(emp.getString("name"));
						editedUser.setEmail(emp.getString("email"));
						editedUser.setPassword(deService.encrptyMe(emp.getString("password")));
						editedUser.setUserName(emp.getString("userName"));
						editedUser.setMobile(emp.getString("mobile"));

						editedEmployee.setId(Integer.parseInt(emp.getString("eid")));
						editedEmployee.setAddedBy(currentUser.getEmployee().getUser().getId());
						editedEmployee.setRoleId(Integer.parseInt(emp.getString("role")));
						editedEmployee.setNic(emp.getString("nic"));
						editedEmployee.setAddress(emp.getString("address"));
						editedEmployee.setContact(emp.getString("contact"));
						editedEmployee.setDob(emp.getString("dob"));
						editedEmployee.setUser(editedUser);

						returnResult = emService.updateUser(editedEmployee);
					}
				} else {
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
