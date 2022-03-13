package com.curewell.invoicemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.curewell.invoicemanagement.entities.CurewellOrderMaster;
import com.curewell.invoicemanagement.entities.CurewellOrderdetail;
import com.curewell.invoicemanagement.entities.repository.CurewellOrderMasterRepository;
import com.curewell.invoicemanagement.vo.CurewellInvoiceOrderVO;

@SpringBootTest
class CurewellinvoicemanagementApplicationTests {

	@Autowired
	CurewellOrderMasterRepository curewellrepository;

	@Test
	void OrderSaveEntity() {

		CurewellOrderMaster ordermaster = new CurewellOrderMaster();
		CurewellOrderdetail orderdetail = new CurewellOrderdetail();
		ordermaster.setAdvancepaid(Double.valueOf(5));
		ordermaster.setInvoicenumber("1-2022");

		Date sqlDateinvoice = java.sql.Date.valueOf("2022-01-18");
		ordermaster.setInvoicedate(sqlDateinvoice);
		ordermaster.setPartyaddress("Ram Enterprise Balasore");
		ordermaster.setPartygstn("XYX777TY");
		ordermaster.setPartystate("Odisha");
		ordermaster.setPartytobill("Ram Enterprise Balasore");
		ordermaster.setPodate(sqlDateinvoice);
		ordermaster.setPonumber("PO-123");
		ordermaster.setStatecode("32");
		ordermaster.setSupplyplace("Vishakhapatnum");

		orderdetail.setCgstpercent(5);
		orderdetail.setProductprice(Double.valueOf(100));
		orderdetail.setQuantity(1);
		orderdetail.setSgstpercent(5);
		orderdetail.setTotalpriceaftertax(105);
		orderdetail.setTotalpricebeforetax(100);
		ordermaster.addOrderDetails(orderdetail);

		CurewellOrderMaster ordermastersaved = curewellrepository.save(ordermaster);

		assertNotNull(ordermastersaved);

		System.out.println(ordermastersaved);

	}

	@Test
	public void saveOrderInvoiceOne() throws URISyntaxException {
		
		CurewellOrderMaster ordermaster = new CurewellOrderMaster();
		CurewellOrderdetail orderdetail = new CurewellOrderdetail();
		ordermaster.setAdvancepaid(Double.valueOf(5));
		ordermaster.setInvoicenumber("1-2022");

		Date sqlDateinvoice = java.sql.Date.valueOf("2022-01-18");
		ordermaster.setInvoicedate(sqlDateinvoice);
		ordermaster.setPartyaddress("Ram Enterprise Balasore");
		ordermaster.setPartygstn("XYX777TY");
		ordermaster.setPartystate("Odisha");
		ordermaster.setPartytobill("Sara Enterprise Jajpur");
		ordermaster.setPodate(sqlDateinvoice);
		ordermaster.setPonumber("PO-123");
		ordermaster.setStatecode("32");
		ordermaster.setSupplyplace("Vishakhapatnum");

		orderdetail.setCgstpercent(5);
		orderdetail.setProductprice(Double.valueOf(100));
		orderdetail.setQuantity(1);
		orderdetail.setSgstpercent(5);
		orderdetail.setTotalpriceaftertax(105);
		orderdetail.setTotalpricebeforetax(100);
		ordermaster.addOrderDetails(orderdetail);

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		final String baseUrl = "http://localhost:8080/curewellproduct/saveorderinvoice/";
		URI uri = new URI(baseUrl);
		


		CurewellInvoiceOrderVO[] prodcuctCreated=  rt.postForObject(uri, ordermaster, CurewellInvoiceOrderVO[].class);
		List<CurewellInvoiceOrderVO> listProduct = Lists.newArrayList(prodcuctCreated);
		listProduct.forEach((p->System.out.println(p.toString())));
	}
	
	@Test
	public void saveOrderInvoiceMultiple() throws URISyntaxException {
		
		CurewellOrderMaster ordermaster = new CurewellOrderMaster();
		CurewellOrderdetail orderdetail = new CurewellOrderdetail();
		CurewellOrderdetail orderdetail2 = new CurewellOrderdetail();
		ordermaster.setAdvancepaid(Double.valueOf(5));
		ordermaster.setInvoicenumber("2-2022");

		Date sqlDateinvoice = java.sql.Date.valueOf("2022-01-18");
		ordermaster.setInvoicedate(sqlDateinvoice);
		ordermaster.setPartyaddress("Saraswati Enterprise Jajpur");
		ordermaster.setPartygstn("XYX777TY");
		ordermaster.setPartystate("Odisha");
		ordermaster.setPartytobill("Saraswati Enterprise Jajpur");
		ordermaster.setPodate(sqlDateinvoice);
		ordermaster.setPonumber("PO-123");
		ordermaster.setStatecode("32");
		ordermaster.setSupplyplace("Vishakhapatnum");

		orderdetail.setCgstpercent(5);
		orderdetail.setProductprice(Double.valueOf(100));
		orderdetail.setQuantity(1);
		orderdetail.setSgstpercent(5);
		orderdetail.setTotalpriceaftertax(105);
		orderdetail.setTotalpricebeforetax(100);
		orderdetail.setProductcode("123");
		ordermaster.addOrderDetails(orderdetail);
		orderdetail2.setCgstpercent(5);
		orderdetail2.setProductprice(Double.valueOf(200));
		orderdetail2.setQuantity(1);
		orderdetail2.setSgstpercent(5);
		orderdetail2.setTotalpriceaftertax(210);
		orderdetail2.setTotalpricebeforetax(200);
		orderdetail2.setProductcode("125");
		ordermaster.addOrderDetails(orderdetail2);

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		final String baseUrl = "http://localhost:8080/curewellproduct/saveorderinvoice/";
		URI uri = new URI(baseUrl);
		


		CurewellInvoiceOrderVO[] prodcuctCreated=  rt.postForObject(uri, ordermaster, CurewellInvoiceOrderVO[].class);
		List<CurewellInvoiceOrderVO> listProduct = Lists.newArrayList(prodcuctCreated);
		listProduct.forEach((p->System.out.println(p.toString())));
	}
	
	@Test
	public void addItemtoExistingOrder() throws URISyntaxException {
		
		CurewellOrderMaster ordermaster = new CurewellOrderMaster();
		CurewellOrderdetail orderdetail = new CurewellOrderdetail();


		orderdetail.setCgstpercent(5);
		orderdetail.setProductprice(Double.valueOf(130));
		orderdetail.setQuantity(3);
		orderdetail.setSgstpercent(5);
		orderdetail.setProductcode("135");
		ordermaster.addOrderDetails(orderdetail);

		RestTemplate rt = new RestTemplate();

		final String baseUrl = "http://localhost:8080/curewellproduct/saveorderinvoicebyorderid/22";
		URI uri = new URI(baseUrl);
		


		CurewellInvoiceOrderVO[] prodcuctCreated=  rt.postForObject(uri,orderdetail, CurewellInvoiceOrderVO[].class);
		List<CurewellInvoiceOrderVO> listProduct = Lists.newArrayList(prodcuctCreated);
		listProduct.forEach((p->System.out.println(p.toString())));
	}


}
