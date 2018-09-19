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

import com.vithu.uscms.entities.Brand;
import com.vithu.uscms.entities.ItemType;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.others.ValueValidator;
import com.vithu.uscms.service.product.BrandManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose  Controller for Add/Edit/Delete/View Single/View All Brands
 */

@Controller
public class BrandManagementController {
	private BrandManagementService brandService = new BrandManagementService();

	private String response;

	// VIEW BRAND
	@CrossOrigin
	@RequestMapping("/brand")
	public String viewBrand(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "","","","");
		
		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {
			
						returnResult = brandService.getAllBrands();
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
			}
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
	
	// DISABLE BRAND
	@CrossOrigin
	@RequestMapping(value = "/deleteBrand/{id}")
	@ResponseBody
	public String deleteBrand(@RequestParam("token") String token, HttpServletRequest request, Model model,
			@PathVariable String id) {

		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_VEHICLE) != null) {
					returnResult = brandService.deleteBrand(Integer.parseInt(id));
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
	@CrossOrigin
	@RequestMapping(value = "/addBrand", method = RequestMethod.POST)
	@ResponseBody
	public String addProduct(@RequestParam("token") String token, HttpServletRequest request) {

		Brand addBrand = new Brand();
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {
					
					JSONObject brand = new JSONObject(request.getParameter("data"));
					
					// Validate Brand Name
					GenericResult validateCode = ValueValidator.validateText(brand.getString("name"), "Name");
					if (validateCode.isStatus()) {

						addBrand.setName(brand.getString("name"));
						addBrand.setDescription(brand.getString("description"));

												returnResult = brandService.addBrand(addBrand);

					} else {
						returnResult = new GenericResult(false, "Name " + MessageConstant.MSG_EMPTY, "Name");
					}

				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
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

	// UPDATE EXISITING PRODUCT
	@CrossOrigin
	@RequestMapping(value = "/updateBrand", method = RequestMethod.POST)
	@ResponseBody
	public String updateProduct(@RequestParam("token") String token, HttpServletRequest request) {

		Brand updateBrand = new Brand();
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {
					
					JSONObject brand = new JSONObject(request.getParameter("data"));
					
					// Validate Brand Name
					GenericResult validateCode = ValueValidator.validateText(brand.getString("name"), "Name");
					if (validateCode.isStatus()) {
						updateBrand.setId(brand.getInt("id"));
						updateBrand.setName(brand.getString("name"));
						updateBrand.setDescription(brand.getString("description"));

												returnResult = brandService.updateBrand(updateBrand);

					} else {
						returnResult = new GenericResult(false, "Name " + MessageConstant.MSG_EMPTY, "Name");
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
