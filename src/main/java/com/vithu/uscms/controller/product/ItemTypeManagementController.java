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
import com.vithu.uscms.entities.ProductType;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.others.ValueValidator;
import com.vithu.uscms.service.product.BrandManagementService;
import com.vithu.uscms.service.product.ItemTypeManagementService;
import com.vithu.uscms.service.product.ProductTypeManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th April 2018
 * @Purpose Controller for Add/Edit/Delete/View Single/View All Item types
 */

@Controller
public class ItemTypeManagementController {
	private ItemTypeManagementService itemTypeService = new ItemTypeManagementService();

	private String response;

	// VIEW ITEM TYPES
	@RequestMapping("/itemType")
	public String viewItemType(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "", "");
		GenericResult mandatoryResult = new GenericResult(false, MessageConstant.MSG_FAILED, " ", " ");

		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					returnResult = itemTypeService.getAllItemTypes();
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "You have not authority to do thi task.");
				}
			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}

		ProductTypeManagementService proTypeService = new ProductTypeManagementService();
		mandatoryResult = proTypeService.getAllProductTypes();
		model.addAttribute("proTypes", mandatoryResult);

		if (URLFormatter.MEDIA_JSON.equals(mediaType)) {
			System.out.println("hi json");
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			response = "jsonview";
			System.out.println("**************" + response);
		} else {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("itemTypes", returnResult);
			response = "itemType";
		}
		return response;
	}

	// DISABLE ITEM TYPE
	@CrossOrigin
	@RequestMapping(value = "/deleteItemType/{id}")
	@ResponseBody
	public String deleteItemType(@RequestParam("token") String token, HttpServletRequest request, Model model,
			@PathVariable String id) {

		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_VEHICLE) != null) {
					returnResult = itemTypeService.deleteItemType(Integer.parseInt(id));
				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH,
							"You have not authority to do thi task.");
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

	// ADD NEW ITEM TYPE
	@CrossOrigin
	@RequestMapping(value = "/addItemType", method = RequestMethod.POST)
	@ResponseBody
	public String addItemType(@RequestParam("token") String token, HttpServletRequest request) {

		ItemType addItemType = new ItemType();
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					JSONObject itemType = new JSONObject(request.getParameter("data"));

					// Validate Brand Name
					GenericResult validateCode = ValueValidator.validateText(itemType.getString("name"), "Name");
					if (validateCode.isStatus()) {

						addItemType.setName(itemType.getString("name"));
						addItemType.setDescription(itemType.getString("description"));
						
						ProductType proType = new ProductType();
						proType.setId(itemType.getInt("proType"));
						
						addItemType.setProType(proType);
						addItemType.setAddedBy(currentUser.getEmployee());
						returnResult = itemTypeService.addItemtype(addItemType);

					} else {
						returnResult = new GenericResult(false, "Name " + MessageConstant.MSG_EMPTY,
								"Name cannot be empty:");
					}

				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH,
							"You have not authority to do thi task.");
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

	// UPDATE EXISITING ITEM TYPE
	@CrossOrigin
	@RequestMapping(value = "/updateItemType", method = RequestMethod.POST)
	@ResponseBody
	public String updateItemtype(@RequestParam("token") String token, HttpServletRequest request) {

		ItemType updateItemType = new ItemType();
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);

		try {

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					JSONObject itemType = new JSONObject(request.getParameter("data"));

					// Validate Item Type Name
					GenericResult validateCode = ValueValidator.validateText(itemType.getString("name"), "Name");
					if (validateCode.isStatus()) {
						updateItemType.setId(Integer.parseInt(itemType.getString("id")));
						updateItemType.setName(itemType.getString("name"));
						updateItemType.setDescription(itemType.getString("description"));

						ProductType proType = new ProductType();
						proType.setId(itemType.getInt("proType"));
						
						updateItemType.setProType(proType);
						returnResult = itemTypeService.updateItemType(updateItemType);

					} else {
						returnResult = new GenericResult(false, "Name " + MessageConstant.MSG_EMPTY,
								"Name cannot be empty.");
					}

				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH,
							"You have not authority to do thi task.");
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
