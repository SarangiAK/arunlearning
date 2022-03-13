package com.curewell.invoicemanagement.restcontroller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curewell.invoicemanagement.entities.CurewellOrderMaster;
import com.curewell.invoicemanagement.entities.CurewellOrderdetail;
import com.curewell.invoicemanagement.entities.CurewellProduct;
import com.curewell.invoicemanagement.entities.repository.CurewellProductDAO;
import com.curewell.invoicemanagement.service.CurewellService;
import com.curewell.invoicemanagement.vo.CurewellInvoiceOrderVO;

@RestController
@CrossOrigin(origins = "http://localhost:9095")
@RequestMapping("/curewellproduct")
public class CurewellController {
	@Autowired
	CurewellProductDAO productdao;
	@Autowired
	CurewellService curewellservice;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurewellController.class); 
	
	@RequestMapping(value = "/getallproducts/", method = RequestMethod.GET)
	public List<CurewellProduct> getAllProducts()
	{
		return productdao.findAll();
	}
	
	@RequestMapping(value = "/getproductbyid/{id}/", method = RequestMethod.GET)
	public CurewellProduct getProductsById(@PathVariable("id") int id )
	{
		LOGGER.info("Fetching the Prodcuct Details");
		return productdao.findById(id).get();
		
	}
	
	@RequestMapping(value = "/getorderbyid/{id}/", method = RequestMethod.GET)
	public List<CurewellInvoiceOrderVO> getInvoiceById(@PathVariable("id") Long id )
	{
		LOGGER.info("Fetching the Invoice Details by OrderId:"+id);
		return curewellservice.getInvoiceByOrderId(id);
	}
	
	@RequestMapping(value = "/saveorderinvoice/", method = RequestMethod.POST)
	public List<CurewellInvoiceOrderVO> saveInvoiceOrder(@RequestBody CurewellOrderMaster orderinvoice )
	{
		LOGGER.info("Saving the Invoice Details:");
		return curewellservice.saveCurewellInvoiceOrder(orderinvoice);
	}
	
	@RequestMapping(value = "/saveorderinvoicebyorderid/{id}", method = RequestMethod.POST)
	public List<CurewellInvoiceOrderVO> saveInvoiceOrder(@PathVariable("id") Long id,@RequestBody CurewellOrderdetail orderItem )
	{
		LOGGER.info("Saving the Invoice Details:");
		return curewellservice.saveCurewellInvoiceOrderAdd(id,orderItem);
	}


}
