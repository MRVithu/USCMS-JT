package com.vithu.uscms.controller.otherMaster;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.service.otherMaster.DepartmentManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

/**
 * @author M.Vithusanth
 * @CreatedOn 23rd April 2018
 * @Purpose  Controller for Add/Edit/Delete/View Single/View All Departments
 */

@Controller
public class DepartmentManagementController {
	private DepartmentManagementService deptService = new DepartmentManagementService();

	private String response;

	// VIEW DEPARTMENTS
	@RequestMapping("/department")
	public String viewDepartment(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "","","","");
		
		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {
			
						returnResult = deptService.getAllDepartments();
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}
		
		if(URLFormatter.MEDIA_JSON.equals(mediaType))
		{
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			response = "jsonview";
		}
		else
		{
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("departments", returnResult);
			response = "department";
		}
		return response;
	}
}
