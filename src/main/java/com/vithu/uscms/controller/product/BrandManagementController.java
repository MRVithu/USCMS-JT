package com.vithu.uscms.controller.product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.service.product.BrandManagementService;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose  Controller for Add/Edit/Delete/View Single/View All Brands
 */

@Controller
public class BrandManagementController {
	private BrandManagementService brandService = new BrandManagementService();

	private String response;

	// VIEW CUSTOMER
	@RequestMapping("/brand")
	public String viewCustomer(@RequestParam("token") String token, HttpServletRequest request, Model model) {
//		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "","","","");
		
		try {
//			if (currentUser == null) {
//				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
//			} else if (currentUser != null) {
//				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {
			
						returnResult = brandService.getAllBrands();
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
			model.addAttribute("brands", returnResult);
			response = "brand";
		}
		return response;
	}
}
