package com.vithu.uscms.controller.product;

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

import com.vithu.uscms.entities.ProductType;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.others.ValueValidator;
import com.vithu.uscms.service.product.ProductTypeManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose  Controller for Add/Edit/Delete/View Single/View All Product types
 */

@Controller
public class ProductTypeManagementController {
	private ProductTypeManagementService proTypeService = new ProductTypeManagementService();

	private String response;

	// VIEW PRODUCT TYPES
	@RequestMapping("/productType")
	public String viewProductType(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "","","","");
		
		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {
			
						returnResult = proTypeService.getAllProductTypes();
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "You have not authority to do thi task.");
				}
			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}
		
		if(URLFormatter.MEDIA_JSON.equals(mediaType))
		{
//			System.out.println("hi json");
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			response = "jsonview";
		}
		else
		{
//			System.out.println("hi not json");
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("productTypes", returnResult);
			response = "productType";
		}
		return response;
	}
	
	// DISABLE PRODUCT TYPE
	@CrossOrigin
	@RequestMapping(value = "/deleteProductType/{id}")
	@ResponseBody
	public String deleteProType(@RequestParam("token") String token, HttpServletRequest request, Model model,
			@PathVariable String id) {

		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {
					returnResult = proTypeService.deleteProType(Integer.parseInt(id));
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "You have not authority to do thi task.");
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

	// ADD NEW PRODUCT TYPE
	@CrossOrigin
	@RequestMapping(value = "/addProductType", method = RequestMethod.POST)
	@ResponseBody
	public String addProduct(@RequestParam("token") String token, HttpServletRequest request) {

		ProductType addProType = new ProductType();
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					JSONObject proType = new JSONObject(request.getParameter("data"));

					// Validate Brand Name
					GenericResult validateCode = ValueValidator.validateText(proType.getString("name"), "Name");
					if (validateCode.isStatus()) {

						addProType.setName(proType.getString("name"));
						addProType.setDescription(proType.getString("description"));
						addProType.setAddedBy(currentUser.getEmployee());
						returnResult = proTypeService.addProType(addProType);

					} else {
						returnResult = new GenericResult(false, "Name " + MessageConstant.MSG_EMPTY, "Name cannot be empty.");
					}

				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "You have not authority to do thi task.");
				}
				returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			}
		} catch (

		Exception e) {
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

	// UPDATE EXISITING PRODUCT TYPE
	@CrossOrigin
	@RequestMapping(value = "/updateProductType", method = RequestMethod.POST)
	@ResponseBody
	public String updateProduct(@RequestParam("token") String token, HttpServletRequest request) {

		ProductType addProType = new ProductType();
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					JSONObject proType = new JSONObject(request.getParameter("data"));

					// Validate Brand Name
					GenericResult validateCode = ValueValidator.validateText(proType.getString("name"), "Name");
					if (validateCode.isStatus()) {
						addProType.setId(proType.getInt("id"));
						addProType.setName(proType.getString("name"));
						addProType.setDescription(proType.getString("description"));

						returnResult = proTypeService.updateProType(addProType);

					} else {
						returnResult = new GenericResult(false, "Name " + MessageConstant.MSG_EMPTY, "Name cannot be empty.");
					}

				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "You have not authority to do thi task.");
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
