package com.vithu.uscms.controller.home;
import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Vithusanth
 * @CreatedOn 11th Sep 2018
 * @Purpose Controller for Home page
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.service.purchase.PurchaseOrderManagementService;
import com.vithu.uscms.service.sales.SalesManagementService;
import com.vithu.uscms.service.user.CustomerManagementService;
import com.vithu.uscms.service.user.SupplierManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;
import com.vithu.uscms.sevice.home.HomeManagementService;

@Controller
public class HomeManagementController {
	private HomeManagementService homeService = new HomeManagementService();

	private String response;
	
	@RequestMapping("/home")
	public String home( HttpServletRequest request, Model model) {
//		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "", "");
		GenericResult mandatoryResult = new GenericResult(false, MessageConstant.MSG_FAILED, " ", " ");
		int todayRegisteredSuppliers = 0;
		int todayRegisteredCustomers = 0;
		int todayPurchaseOrders = 0;
		
		try {
//			if (currentUser == null) {
//				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
//			} else if (currentUser != null) {
//				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					
//				} else {
//					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
//				}
//			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}

		SalesManagementService salesService = new SalesManagementService();
		mandatoryResult = salesService.getAllSales();

		System.out.println(mandatoryResult.getResultString());
		model.addAttribute("sales", mandatoryResult);
		
		SupplierManagementService supplierService = new SupplierManagementService();
		todayRegisteredSuppliers = supplierService.getTodayRegisteredSuppliers();
		model.addAttribute("todayRegisteredSupplier", todayRegisteredSuppliers);
		
		CustomerManagementService customerrService = new CustomerManagementService();
		todayRegisteredCustomers = customerrService.getTodayRegisteredCustomers();
		model.addAttribute("todayRegisteredCustomers", todayRegisteredCustomers);
		
		PurchaseOrderManagementService poService = new PurchaseOrderManagementService();
		todayPurchaseOrders = poService.getTodayPurchaseOrders();
		model.addAttribute("todayPurchaseOrders", todayPurchaseOrders);
		
		if (URLFormatter.MEDIA_JSON.equals(mediaType)) {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			response = "jsonview";
		} else {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("count", returnResult);
			response = "home";
		}
		return response;
	}
}


