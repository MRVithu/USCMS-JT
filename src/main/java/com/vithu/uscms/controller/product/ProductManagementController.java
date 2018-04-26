package com.vithu.uscms.controller.product;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose  Controller for Add/Edit/Delete/View Single/View All Brands
 */

import javax.servlet.http.HttpServletRequest;

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
import com.vithu.uscms.entities.ItemType;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.others.ValueValidator;
import com.vithu.uscms.service.product.BrandManagementService;
import com.vithu.uscms.service.product.ItemTypeManagementService;
import com.vithu.uscms.service.product.ProductManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

@Controller
public class ProductManagementController {
	private ProductManagementService proService = new ProductManagementService();

	private String response;

	// VIEW CUSTOMER
	@RequestMapping("/product")
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

					returnResult = proService.getAllProducts();
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}

		BrandManagementService brandService = new BrandManagementService();
		mandatoryResult = brandService.getAllBrands();
		model.addAttribute("brands", mandatoryResult);

		ItemTypeManagementService itemTypeService = new ItemTypeManagementService();
		mandatoryResult = itemTypeService.getAllItemTypes();
		model.addAttribute("itemTypes", mandatoryResult);

		if (URLFormatter.MEDIA_JSON.equals(mediaType)) {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			response = "jsonview";
		} else {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("products", returnResult);
			response = "product";
		}
		return response;
	}

	// DISABLE Product
	@RequestMapping(value = "/deleteProduct/{id}")
	@ResponseBody
	public String deleteVehicle(@RequestParam("token") String token, HttpServletRequest request, Model model,
			@PathVariable String id) {

		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_VEHICLE) != null) {
					returnResult = proService.deleteProduct(Integer.parseInt(id));
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

	// ADD NEW PRODUCT
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	@ResponseBody
	public String addVehicle(@RequestParam("token") String token, HttpServletRequest request) {

		Product addProduct = new Product();
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);
		System.out.println("ADD NEW PRODUCT");
		try {
			JSONObject product = new JSONObject(request.getParameter("data"));
			
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					// Validate Product Code
					GenericResult validateResult = ValueValidator.validateText(product.getString("code"), "Code");
					if (validateResult.isStatus()) {
						addProduct.setCode(product.getString("code"));
						addProduct.setName(product.getString("name"));
						addProduct.setDescription(product.getString("description"));
						addProduct.setDiscount(product.getDouble("discount"));
						addProduct.setLastPurchasePrice(product.getDouble("purPrice"));
						addProduct.setMinPrice(product.getDouble("minPrice"));
						addProduct.setSelleingPrice(product.getDouble("salesPrice"));
						addProduct.setAddedBy(currentUser.getEmployee());
						
						Brand brand =new Brand();
						brand.setId(product.getInt("brand"));
						addProduct.setBrand(brand);
						
						ItemType itemType = new ItemType();
						itemType.setId(product.getInt("itemType"));
						addProduct.setItemType(itemType);
						
						returnResult = proService.addProduct(addProduct);
					} else {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Code");
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

	// UPDATE EXISITING PRODUCT
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	@ResponseBody
	public String updateProduct(@RequestParam("token") String token, HttpServletRequest request) {
System.out.println("===========hi!! this is update ============");
		Product updateProduct = new Product();
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);
		
		try {
			JSONObject product = new JSONObject(request.getParameter("data"));
			
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					// Validate Product Code
					GenericResult validateResult = ValueValidator.validateText(product.getString("code"), "Code");
					if (validateResult.isStatus()) {
						updateProduct.setId(product.getInt("id"));
						updateProduct.setCode(product.getString("code"));
						updateProduct.setName(product.getString("name"));
						updateProduct.setDescription(product.getString("description"));
						updateProduct.setDiscount(product.getDouble("discount"));
						updateProduct.setLastPurchasePrice(product.getDouble("purPrice"));
						updateProduct.setMinPrice(product.getDouble("minPrice"));
						updateProduct.setSelleingPrice(product.getDouble("salesPrice"));
						
						Brand brand =new Brand();
						brand.setId(product.getInt("brand"));
						updateProduct.setBrand(brand);
						
						ItemType itemType = new ItemType();
						itemType.setId(product.getInt("itemType"));
						updateProduct.setItemType(itemType);
					
						returnResult = proService.updateProduct(updateProduct);
					} else {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY, "Code");
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
