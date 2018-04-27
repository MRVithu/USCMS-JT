package com.vithu.uscms.controller.purchase;
/**
 * @author M.Vithusanth
 * @CreatedOn 27h April 2018
 * @Purpose  Controller for Purchase 
 */

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vithu.uscms.entities.Department;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.entities.Purchase;
import com.vithu.uscms.entities.PurchaseOrder;
import com.vithu.uscms.entities.PurchaseOrderProduct;
import com.vithu.uscms.entities.PurchaseProduct;
import com.vithu.uscms.entities.Supplier;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.others.ValueValidator;
import com.vithu.uscms.service.otherMaster.DepartmentManagementService;
import com.vithu.uscms.service.product.ProductManagementService;
import com.vithu.uscms.service.purchase.PurchaseManagementService;
import com.vithu.uscms.service.user.SupplierManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

@Controller
public class PurchaseManagementController {
	private PurchaseManagementService purchaseService = new PurchaseManagementService();

	private String response;

	// VIEW PURCHASE
	@RequestMapping("/purchase")
	public String viewPurchaseOrder(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "", "");

		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					returnResult = purchaseService.getAllPurchases();
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}

		if (URLFormatter.MEDIA_JSON.equals(mediaType)) {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			response = "jsonview";
		} else {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("purchases", returnResult);
			response = "purchase";
		}
		return response;
	}

	// VIEW PURCHASE ADD PAGE DETAILS
	@RequestMapping("/purchaseAddView")
	public String viewProduct(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "", "");
		GenericResult mandatoryResult = new GenericResult(false, MessageConstant.MSG_FAILED, " ", " ");

		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					ProductManagementService proService = new ProductManagementService();
					returnResult = proService.getAllProducts();

				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}

		SupplierManagementService supplierService = new SupplierManagementService();
		mandatoryResult = supplierService.getAllSuppliers();
		model.addAttribute("suppliers", mandatoryResult);

		DepartmentManagementService deptService = new DepartmentManagementService();
		mandatoryResult = deptService.getAllDepartments();
		model.addAttribute("departments", mandatoryResult);

		if (URLFormatter.MEDIA_JSON.equals(mediaType)) {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			response = "jsonview";
		} else {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("products", returnResult);
			response = "purchaseAdd";
		}
		return response;
	}

	// ADD NEW PURCHASE 
	@RequestMapping(value = "/addPurchase", method = RequestMethod.POST)
	@ResponseBody
	public String addPurchaseOrder(@RequestParam("token") String token, HttpServletRequest request) {

		Purchase newPurchase = new Purchase();
		
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);
		System.out.println("ADD NEW PURCHASE");

		try {
			JSONObject purOrder = new JSONObject(request.getParameter("data"));

			JSONArray purchaseProducts = purOrder.getJSONArray("products");
			List<PurchaseProduct> purProductsList = new ArrayList<PurchaseProduct>();

			for (int i = 0; i < purchaseProducts.length(); i++) {
				PurchaseProduct purProduct = new PurchaseProduct();
				
				purProduct.setQty(purchaseProducts.getJSONObject(i).getDouble("quantity"));

				Product product = new Product();
				product.setId(purchaseProducts.getJSONObject(i).getInt("id"));
				product.setName(purchaseProducts.getJSONObject(i).getString("name"));

				purProduct.setProduct(product);
				purProduct.setUnitPrice(purchaseProducts.getJSONObject(i).getDouble("purchasePrice"));

				purProductsList.add(purProduct);
			}

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					// Validate Product Code
					GenericResult validateResult = ValueValidator.validateText(purOrder.getString("code"), "Code");
					if (validateResult.isStatus()) {
						
						newPurchase.setCode(purOrder.getString("code"));
						newPurchase.settDate(purOrder.getString("poDate"));
						newPurchase.setNote(purOrder.getString("note"));

						Department dept = new Department();
						dept.setId(purOrder.getInt("department"));
						newPurchase.setDept(dept);

						Supplier supplier = new Supplier();
						supplier.setId(purOrder.getInt("supplier"));
						newPurchase.setSupplier(supplier);

						newPurchase.setPurProduct(purProductsList);
						newPurchase.setAddedBy(currentUser.getEmployee());

						returnResult = purchaseService.addPurchase(newPurchase);
					} else {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Purchase Code can not be empty.");
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
			response = JsonFormer.form(returnResult);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
