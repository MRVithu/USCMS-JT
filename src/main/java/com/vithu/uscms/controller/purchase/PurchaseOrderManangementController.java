package com.vithu.uscms.controller.purchase;
/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose  Controller for Purchase order
 */

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vithu.uscms.entities.Brand;
import com.vithu.uscms.entities.Department;
import com.vithu.uscms.entities.ItemType;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.entities.PurchaseOrder;
import com.vithu.uscms.entities.PurchaseOrderProduct;
import com.vithu.uscms.entities.Supplier;
import com.vithu.uscms.entities.User;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.others.ValueValidator;
import com.vithu.uscms.service.otherMaster.DepartmentManagementService;
import com.vithu.uscms.service.product.BrandManagementService;
import com.vithu.uscms.service.product.ItemTypeManagementService;
import com.vithu.uscms.service.product.ProductManagementService;
import com.vithu.uscms.service.purchase.PurchaseOrderManagementService;
import com.vithu.uscms.service.user.SupplierManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

@Controller
public class PurchaseOrderManangementController {
	private PurchaseOrderManagementService purOrderService = new PurchaseOrderManagementService();

	private String response;

	// VIEW PURCHASE ORDERS
	@RequestMapping("/purchaseOrder")
	public String viewPurchaseOrder(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "", "");

		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					returnResult = purOrderService.getAllPurchaseOrders();
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
			model.addAttribute("purchaseOrders", returnResult);
			response = "purchaseOrder";
		}
		return response;
	}

	// CLOSE PURCHASE ORDER
	@RequestMapping(value = "/closePurchaseOrder/{id}")
	@ResponseBody
	public String closePurchaseorder(@RequestParam("token") String token, HttpServletRequest request, Model model,
			@PathVariable String id) {

		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_VEHICLE) != null) {
					returnResult = purOrderService.closepurchaseOrder(Integer.parseInt(id));
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}
		try {
			response = JsonFormer.form(returnResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return response;
	}

	// VIEW PURCHASE ADD ORDERS PAGE DETAILS
	@RequestMapping("/purchaseOrderAddView")
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
			response = "purchaseOrderAdd";
		}
		return response;
	}

	// ADD NEW PURCHASE ORDER
	@RequestMapping(value = "/addPurchaseOrder", method = RequestMethod.POST)
	@ResponseBody
	public String addPurchaseOrder(@RequestParam("token") String token, HttpServletRequest request) {

		PurchaseOrder addPurchaseOrder = new PurchaseOrder();
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);
		System.out.println("ADD NEW PURCHASE ORDER");
		
		try {
			JSONObject purOrder = new JSONObject(request.getParameter("data"));


			JSONArray purchaseOrderProducts = purOrder.getJSONArray("products");
			List<PurchaseOrderProduct> poProductList = new ArrayList<PurchaseOrderProduct>();

			for (int i = 0; i < purchaseOrderProducts.length(); i++) {
				PurchaseOrderProduct poProduct = new PurchaseOrderProduct();
				poProduct.setQty(purchaseOrderProducts.getJSONObject(i).getDouble("quantity"));
				
				Product product=new Product();
				product.setId(purchaseOrderProducts.getJSONObject(i).getInt("id"));
				product.setName(purchaseOrderProducts.getJSONObject(i).getString("name"));
				
				poProduct.setProduct(product);
				poProduct.setUnitPrice(purchaseOrderProducts.getJSONObject(i).getDouble("purchasePrice"));
				
				poProductList.add(poProduct);
			}
			
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					// Validate Product Code
					GenericResult validateResult = ValueValidator.validateText(purOrder.getString("code"), "Code");
					if (validateResult.isStatus()) {
						addPurchaseOrder.setCode(purOrder.getString("code"));
						addPurchaseOrder.settDate(purOrder.getString("poDate"));
						addPurchaseOrder.setExpectedDate(purOrder.getString("exDate"));
						addPurchaseOrder.setNote(purOrder.getString("note"));
						
						Department dept =new Department();
						dept.setId(purOrder.getInt("department"));
						addPurchaseOrder.setDept(dept);
						
						Supplier supplier= new Supplier();
						supplier.setId(purOrder.getInt("supplier"));
						addPurchaseOrder.setSupplier(supplier);
						
						addPurchaseOrder.setPoProduct(poProductList);
						addPurchaseOrder.setAddedBy(currentUser.getEmployee());
						
						returnResult = purOrderService.addPurchaseOrder(addPurchaseOrder);
					} else {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Purchase order code can not be empty.");
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
