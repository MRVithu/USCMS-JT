package com.vithu.uscms.controller.sales;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vithu.uscms.entities.Bank;
import com.vithu.uscms.entities.Customer;
import com.vithu.uscms.entities.Department;
import com.vithu.uscms.entities.Employee;
import com.vithu.uscms.entities.PayCash;
import com.vithu.uscms.entities.PayCheque;
import com.vithu.uscms.entities.PayCredit;
import com.vithu.uscms.entities.Payment;
import com.vithu.uscms.entities.Product;
import com.vithu.uscms.entities.Sales;
import com.vithu.uscms.entities.SalesProduct;
import com.vithu.uscms.others.GenericResult;
import com.vithu.uscms.others.JsonFormer;
import com.vithu.uscms.others.MessageConstant;
import com.vithu.uscms.others.URLFormatter;
import com.vithu.uscms.others.ValueValidator;
import com.vithu.uscms.service.otherMaster.DepartmentManagementService;
import com.vithu.uscms.service.product.ProductManagementService;
import com.vithu.uscms.service.sales.SalesManagementService;
import com.vithu.uscms.service.user.CustomerManagementService;
import com.vithu.uscms.service.user.EmployeeManagementService;
import com.vithu.uscms.session.AuthorityConstant;
import com.vithu.uscms.session.CurrentUser;
import com.vithu.uscms.session.TokenManager;

/**
 * @author M.Vithusanth
 * @CreatedOn 28th April 2018
 * @Purpose Controller for Sales
 */

@CrossOrigin
@Controller
public class SalesManagementController {
	private SalesManagementService salesService = new SalesManagementService();

	private String response;

	// VIEW SALES
	@RequestMapping("/sales")
	public String viewSales(@RequestParam("token") String token, HttpServletRequest request, Model model) {
		CurrentUser currentUser = TokenManager.validateToken(token);
		String mediaType = URLFormatter.getMediaType(request);
		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "", "", "", "");

		try {
			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					returnResult = salesService.getAllSales();
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
			model.addAttribute("sales", returnResult);
			response = "sales";
		}
		return response;
	}

	// VIEW SALES ADD PAGE DETAILS
	@RequestMapping("/salesAddView")
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
					returnResult = salesService.getMaxSalesId();

				} else {
					returnResult = new GenericResult(false, MessageConstant.MSG_NO_AUTH, "");
				}
			}
		} catch (Exception e) {
			returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, e.toString());
		}

		ProductManagementService proService = new ProductManagementService();
		mandatoryResult = proService.getAllProducts();
		model.addAttribute("products", mandatoryResult);

		CustomerManagementService customerService = new CustomerManagementService();
		mandatoryResult = customerService.getAllCustomers();
		model.addAttribute("customers", mandatoryResult);

		EmployeeManagementService empService = new EmployeeManagementService();
		mandatoryResult = empService.getAllUser();
		model.addAttribute("employees", mandatoryResult);

		DepartmentManagementService deptService = new DepartmentManagementService();
		mandatoryResult = deptService.getAllDepartments();
		model.addAttribute("departments", mandatoryResult);

		if (URLFormatter.MEDIA_JSON.equals(mediaType)) {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_JSON);
			request.setAttribute("response", returnResult);
			response = "jsonview";
		} else {
			returnResult.setRequestedFormat(URLFormatter.MEDIA_PAGE);
			model.addAttribute("salesMaxId", returnResult);
			response = "salesAdd";
		}
		return response;
	}

	// ADD NEW Sales
	@RequestMapping(value = "/addSales", method = RequestMethod.POST)
	@ResponseBody
	public String addPurchaseOrder(@RequestParam("token") String token, HttpServletRequest request) {

		Sales newSales = new Sales();

		GenericResult returnResult = new GenericResult(false, MessageConstant.MSG_FAILED, "");
		CurrentUser currentUser = TokenManager.validateToken(token);
		System.out.println("ADD NEW SALES");

		try {
			JSONObject sales = new JSONObject(request.getParameter("data"));

			if (currentUser == null) {
				returnResult = new GenericResult(false, MessageConstant.MSG_INVALID_TOKEN, "");
			} else if (currentUser != null) {
				if (currentUser.getAuthorityMap().get(AuthorityConstant.AUTH_VIEW_CUSTOMER) != null) {

					// Validate Sales Code
					GenericResult validateResult = ValueValidator.validateText(sales.getString("code"), "Code");
					if (validateResult.isStatus()) {

						// Set sales products
						JSONArray salesProducts = sales.getJSONArray("products");
						List<SalesProduct> salesProductsList = new ArrayList<SalesProduct>();

						for (int i = 0; i < salesProducts.length(); i++) {
							SalesProduct salesProduct = new SalesProduct();

							salesProduct.setQty(salesProducts.getJSONObject(i).getDouble("quantity"));

							Product product = new Product();
							product.setId(salesProducts.getJSONObject(i).getInt("id"));
							product.setName(salesProducts.getJSONObject(i).getString("name"));

							salesProduct.setProduct(product);
							salesProduct.setUnitPrice(salesProducts.getJSONObject(i).getDouble("sellingPrice"));

							salesProductsList.add(salesProduct);
						}

						// Set Pay Cheques
						JSONArray payCheques = sales.getJSONArray("payCheques");
						List<PayCheque> payCheList = new ArrayList<PayCheque>();

						for (int i = 0; i < payCheques.length(); i++) {
							PayCheque payCheque = new PayCheque();

							payCheque.setAmount(+payCheques.getJSONObject(i).getDouble("amount"));
							payCheque.setNumber(payCheques.getJSONObject(i).getString("number"));
							payCheque.setChequeDate(payCheques.getJSONObject(i).getString("date"));

							Bank bank = new Bank();
							bank.setBankName(payCheques.getJSONObject(i).getString("bank"));
							payCheque.setBank(bank);

							payCheList.add(payCheque);
						}

						newSales.setCode(sales.getString("code"));
						newSales.settDate(sales.getString("salesDate"));

						Department dept = new Department();
						dept.setId(sales.getInt("department"));
						newSales.setDept(dept);

						Customer customer = new Customer();
						customer.setId(sales.getInt("customer"));
						newSales.setCustomer(customer);

						Employee so = new Employee();
						so.setId(sales.getInt("salesOfficer"));
						newSales.setSalesOfficer(so);

						PayCash payCash = new PayCash();
						payCash.setAmount(sales.getDouble("payCash"));

						PayCredit payCredit = new PayCredit();
						payCredit.setAmount(sales.getDouble("payCredit"));

						Payment pay = new Payment();
						pay.setPayCheques(payCheList);
						pay.setPayCash(payCash);
						pay.setPayCredit(payCredit);
						pay.setAmount(sales.getDouble("payTotal"));
						newSales.setPay(pay);

						newSales.setSalesProduct(salesProductsList);
						newSales.setAddedBy(currentUser.getEmployee());

						returnResult = salesService.addSales(newSales);
					} else {
						returnResult = new GenericResult(false, MessageConstant.MSG_EMPTY,
								"Sales Code can not be empty.");
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
