package com.curewell.invoicemanagement.restcontroller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.curewell.invoicemanagement.entities.CurewellOrderMaster;
import com.curewell.invoicemanagement.entities.CurewellOrderdetail;
import com.curewell.invoicemanagement.entities.CurewellProduct;
import com.curewell.invoicemanagement.entities.User;
import com.curewell.invoicemanagement.entities.repository.CurewellProductDAO;
import com.curewell.invoicemanagement.service.CurewellService;
import com.curewell.invoicemanagement.vo.CurewellInvoiceOrderVO;
import com.lowagie.text.DocumentException;

@Controller
public class MvcController {

	@Autowired
	private CurewellService curewellservice;
	@Autowired
	private CurewellProductDAO productService;

	@RequestMapping("/")
	public String home() {
		System.out.println("Welcome to Curewell Invoice Management Console");
		return "index";
	}

	@GetMapping("/curewell/registerinvoice")
	public String showForm(Model model) {
		CurewellInvoiceOrderVO curewellinvoiceordervo = new CurewellInvoiceOrderVO();
		curewellinvoiceordervo.setAdvancepaid(0.0);

		model.addAttribute("curewellinvoiceordervo", curewellinvoiceordervo);

		List<CurewellProduct> productlist = productService.findAll();
		List<String> productcodeList = productlist.stream().map(e -> e.getProductdescription())
				.collect(Collectors.toList());
		model.addAttribute("productcodeList", productcodeList);

		return "registerinvoice_form";
	}
	
	@GetMapping("/curewell/registerinvoiceaddmore/registerinvoice")
	public String showFormAdditional(Model model) {
		CurewellInvoiceOrderVO curewellinvoiceordervo = new CurewellInvoiceOrderVO();
		curewellinvoiceordervo.setAdvancepaid(0.0);

		model.addAttribute("curewellinvoiceordervo", curewellinvoiceordervo);

		List<CurewellProduct> productlist = productService.findAll();
		List<String> productcodeList = productlist.stream().map(e -> e.getProductdescription())
				.collect(Collectors.toList());
		model.addAttribute("productcodeList", productcodeList);

		return "registerinvoice_form";
	}


	@RequestMapping(value = "/curewell/registerinvoiceaddmore/{id}", params = "additem", method = RequestMethod.POST)
	public String showAddItemForm(@PathVariable Long id, Model model) {

		CurewellInvoiceOrderVO curewellinvoiceordervo = new CurewellInvoiceOrderVO();
		curewellinvoiceordervo.setAdvancepaid(0.0);

		model.addAttribute("curewellinvoiceordervo", curewellinvoiceordervo);

		final String orderIdValue = id.toString();

		List<CurewellProduct> productlist = productService.findAll();
		List<String> productcodeList = productlist.stream().map(e -> e.getProductdescription())
				.collect(Collectors.toList());

		model.addAttribute("productcodeList", productcodeList);
		model.addAttribute("orderIdValue", orderIdValue);

		return "registerinvoicesingleitem_form";
	}

	@RequestMapping(value = "/curewell/registerinvoicepost", params = "addInvoice", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("curewellinvoiceordervo") CurewellInvoiceOrderVO curewellinvoiceordervo)
			throws ParseException {

		Date date = new Date();
		CurewellOrderMaster invoice = new CurewellOrderMaster();
		CurewellOrderdetail order = new CurewellOrderdetail();
		invoice.setInvoicenumber("");
		invoice.setInvoicedate(date);
		invoice.setPonumber(curewellinvoiceordervo.getPonumber());
		invoice.setPodate(curewellinvoiceordervo.getPodate());
		invoice.setPartytobill(curewellinvoiceordervo.getPartytobill());
		invoice.setPartyaddress(curewellinvoiceordervo.getPartyaddress());
		invoice.setPartygstn(curewellinvoiceordervo.getPartygstn());
		invoice.setPartystate(curewellinvoiceordervo.getPartystate());
		invoice.setStatecode(curewellinvoiceordervo.getStatecode());
		invoice.setAdvancepaid(curewellinvoiceordervo.getAdvancepaid());
		invoice.setSupplyplace(curewellinvoiceordervo.getSupplyplace());
		invoice.setShiptopartyname(curewellinvoiceordervo.getShiptopartyname());
		order.setProductcode(curewellinvoiceordervo.getProductcode());
		order.setQuantity(curewellinvoiceordervo.getQuantity());
		order.setProductprice(curewellinvoiceordervo.getProductprice());
		order.setCgstpercent(curewellinvoiceordervo.getCgstpercent());
		order.setSgstpercent(curewellinvoiceordervo.getSgstpercent());
		invoice.addOrderDetails(order);

		List<CurewellInvoiceOrderVO> orderList = curewellservice.saveCurewellInvoiceOrder(invoice);
		curewellinvoiceordervo.setOutstandingamount(orderList.get(0).getOutstandingamount());
		curewellinvoiceordervo.setOrderid(orderList.get(0).getOrderid());
		curewellinvoiceordervo.setInvoicedate(orderList.get(0).getInvoicedate());
		curewellinvoiceordervo.setTotalpriceaftertax(orderList.get(0).getTotalpriceaftertax());
		curewellinvoiceordervo.setTotalpricebeforetax(orderList.get(0).getTotalpricebeforetax());
		curewellinvoiceordervo.setInvoicenumber(orderList.get(0).getInvoicenumber());

		System.out.println(orderList);
		return "registerinvoice_success";
	}
	
	@RequestMapping(value = "/curewell/registerinvoiceaddmore/registerinvoicepost", params = "addInvoice", method = RequestMethod.POST)
	public String submitFormHome(@ModelAttribute("curewellinvoiceordervo") CurewellInvoiceOrderVO curewellinvoiceordervo)
			throws ParseException {

		Date date = new Date();
		CurewellOrderMaster invoice = new CurewellOrderMaster();
		CurewellOrderdetail order = new CurewellOrderdetail();
		invoice.setInvoicenumber("");
		invoice.setInvoicedate(date);
		invoice.setPonumber(curewellinvoiceordervo.getPonumber());
		invoice.setPodate(curewellinvoiceordervo.getPodate());
		invoice.setPartytobill(curewellinvoiceordervo.getPartytobill());
		invoice.setPartyaddress(curewellinvoiceordervo.getPartyaddress());
		invoice.setPartygstn(curewellinvoiceordervo.getPartygstn());
		invoice.setPartystate(curewellinvoiceordervo.getPartystate());
		invoice.setStatecode(curewellinvoiceordervo.getStatecode());
		invoice.setAdvancepaid(curewellinvoiceordervo.getAdvancepaid());
		invoice.setSupplyplace(curewellinvoiceordervo.getSupplyplace());
		invoice.setShiptopartyname(curewellinvoiceordervo.getShiptopartyname());
		order.setProductcode(curewellinvoiceordervo.getProductcode());
		order.setQuantity(curewellinvoiceordervo.getQuantity());
		order.setProductprice(curewellinvoiceordervo.getProductprice());
		order.setCgstpercent(curewellinvoiceordervo.getCgstpercent());
		order.setSgstpercent(curewellinvoiceordervo.getSgstpercent());
		invoice.addOrderDetails(order);

		List<CurewellInvoiceOrderVO> orderList = curewellservice.saveCurewellInvoiceOrder(invoice);
		curewellinvoiceordervo.setOutstandingamount(orderList.get(0).getOutstandingamount());
		curewellinvoiceordervo.setOrderid(orderList.get(0).getOrderid());
		curewellinvoiceordervo.setInvoicedate(orderList.get(0).getInvoicedate());
		curewellinvoiceordervo.setTotalpriceaftertax(orderList.get(0).getTotalpriceaftertax());
		curewellinvoiceordervo.setTotalpricebeforetax(orderList.get(0).getTotalpricebeforetax());
		curewellinvoiceordervo.setInvoicenumber(orderList.get(0).getInvoicenumber());

		System.out.println(orderList);
		return "registerinvoice_success";
	}

	@RequestMapping(value = "/curewell/registerinvoicepost", params = "gotoHome", method = RequestMethod.POST)
	public String IndexScreenHome() {

		return "index";

	}
	
	@RequestMapping(value = "/curewell/registerinvoicepost", params = "logout", method = RequestMethod.POST)
	public String loginScreenHome(Model loginModel) {
		
		User user = new User();
		loginModel.addAttribute("user", user);

		return "login";

	}
	
	


	@RequestMapping(value = "/curewell/registerinvoiceaddmore/registerinvoicepostitem", params = "additem", method = RequestMethod.POST)
	public String submitFormItem(
			@ModelAttribute("curewellinvoiceordervo") CurewellInvoiceOrderVO curewellinvoiceordervo)
			throws ParseException {

		CurewellOrderdetail order = new CurewellOrderdetail();

		order.setProductcode(curewellinvoiceordervo.getProductcode());
		order.setQuantity(curewellinvoiceordervo.getQuantity());
		order.setProductprice(curewellinvoiceordervo.getProductprice());
		order.setCgstpercent(curewellinvoiceordervo.getCgstpercent());
		order.setSgstpercent(curewellinvoiceordervo.getSgstpercent());
		List<CurewellInvoiceOrderVO> orderList = curewellservice
				.saveCurewellInvoiceOrderAdd(curewellinvoiceordervo.getOrderid(), order);
		int orderLength = orderList.size();
		orderLength = orderLength - 1;
		curewellinvoiceordervo.setOutstandingamount(orderList.get(orderLength).getOutstandingamount());
		curewellinvoiceordervo.setOrderid(orderList.get(orderLength).getOrderid());
		curewellinvoiceordervo.setInvoicedate(orderList.get(orderLength).getInvoicedate());
		curewellinvoiceordervo.setTotalpriceaftertax(orderList.get(orderLength).getTotalpriceaftertax());
		curewellinvoiceordervo.setTotalpricebeforetax(orderList.get(orderLength).getTotalpricebeforetax());

		System.out.println(orderList);
		return "registeroneitem_success";
	}

	@RequestMapping(value = "/curewell/registerinvoiceaddmore/registerinvoicepostitem", params = "logout", method = RequestMethod.POST)
	public String RegisterPostOneItemLogout(Model loginModel) throws ParseException {
		User user = new User();
		loginModel.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(value = "/curewell/registerinvoiceaddmore/registerinvoicepost", params = "logout", method = RequestMethod.POST)
	public String RegisterPostOneItemLogout2(Model loginModel) throws ParseException {
		User user = new User();
		loginModel.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(value = "/curewell/registerinvoiceaddmore/registerinvoicepostitem", params = "gotoHome", method = RequestMethod.POST)
	public String IndexScreenHomeOneItemScreen() {

		return "index";

	}
	
	@RequestMapping(value = "/curewell/registerinvoiceaddmore/registerinvoicepost", params = "gotoHome", method = RequestMethod.POST)
	public String IndexScreenHomeOneItemScreen2() {

		return "index";

	}



	@RequestMapping(value = "/curewell/registerinvoiceaddmore/registerinvoicepostitem/{id}", params = "logout", method = RequestMethod.POST)
	public String submitFormItemLogout(@PathVariable Long id, Model loginModel) throws ParseException {
		User user = new User();
		loginModel.addAttribute("user", user);

		return "login";
	}

	@RequestMapping(value = "/curewell/registerinvoiceaddmore/{id}", params = "logout", method = RequestMethod.POST)
	public String submitFormItemLogoutFirst(@PathVariable Long id, Model loginModel) throws ParseException {
		User user = new User();
		loginModel.addAttribute("user", user);

		return "login";
	}
	
	@RequestMapping(value = "/curewell/registerinvoiceaddmore/{id}", params = "gotoHome", method = RequestMethod.POST)
	public String backHomeOneItem(@PathVariable Long id, Model loginModel) throws ParseException {


		return "index";
	}

	@RequestMapping(value = "/curewell/registerinvoiceaddmore/{id}", params = "generateinvoice", method = RequestMethod.POST)
	public void exportToPDF(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=CureWellInvoice_" + id + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<CurewellInvoiceOrderVO> listUsers = curewellservice.getInvoiceByOrderId(id);

		InvoicePDFExporter exporter = new InvoicePDFExporter(listUsers);
		exporter.export(response);

	}

}
