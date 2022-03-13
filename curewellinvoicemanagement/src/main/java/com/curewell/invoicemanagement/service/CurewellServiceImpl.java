package com.curewell.invoicemanagement.service;

import java.util.ArrayList;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curewell.invoicemanagement.entities.CurewellOrderMaster;
import com.curewell.invoicemanagement.entities.CurewellOrderdetail;
import com.curewell.invoicemanagement.entities.repository.CurewellOrderDetailRepository;
import com.curewell.invoicemanagement.entities.repository.CurewellOrderMasterRepository;
import com.curewell.invoicemanagement.vo.CurewellInvoiceOrderVO;

@Service
public class CurewellServiceImpl implements CurewellService {
	@Autowired
	CurewellOrderMasterRepository orderRepo;
	@Autowired
	CurewellOrderDetailRepository orderdetailRepo;
	private static final Logger LOGGER = LoggerFactory.getLogger(CurewellService.class);

	@Override
	public List<CurewellInvoiceOrderVO> getInvoiceByOrderId(Long orderid) {

		List<CurewellInvoiceOrderVO> vo = new ArrayList<CurewellInvoiceOrderVO>();
		CurewellOrderMaster invoice = orderRepo.findById(orderid).get();
		List<CurewellOrderdetail> orders = new ArrayList<>(invoice.getOrderdetails());
		for (int i = 0; i < orders.size(); i++) {
			CurewellInvoiceOrderVO records = new CurewellInvoiceOrderVO();
			records.setOrderid(invoice.getOrderid());
			records.setInvoicenumber(invoice.getInvoicenumber());
			records.setInvoicedate(invoice.getInvoicedate());
			records.setPonumber(invoice.getPonumber());
			records.setPodate(invoice.getPodate());
			records.setPartytobill(invoice.getPartytobill());
			records.setPartyaddress(invoice.getPartyaddress());
			records.setPartygstn(invoice.getPartygstn());
			records.setPartystate(invoice.getPartystate());
			records.setStatecode(invoice.getStatecode());
			records.setAdvancepaid(invoice.getAdvancepaid());
			records.setOutstandingamount(invoice.getOutstandingamount());
			records.setSupplyplace(invoice.getSupplyplace());
			records.setProductcode(orders.get(i).getProductcode());
			records.setQuantity(orders.get(i).getQuantity());
			records.setProductprice(orders.get(i).getProductprice());
			records.setCgstpercent(orders.get(i).getCgstpercent());
			records.setSgstpercent(orders.get(i).getSgstpercent());
			records.setTotalpricebeforetax(orders.get(i).getTotalpricebeforetax());
			records.setTotalpriceaftertax(orders.get(i).getTotalpriceaftertax());
			records.setShiptopartyname(invoice.getShiptopartyname());
			vo.add(records);
		}

		return vo;
	}

	@Override
	public List<CurewellInvoiceOrderVO> saveCurewellInvoiceOrder(CurewellOrderMaster invoice) {
		List<CurewellInvoiceOrderVO> vo = new ArrayList<CurewellInvoiceOrderVO>();
		List<CurewellOrderdetail> objs = invoice.getOrderdetails();
		Double totalorderAmount = 0.0;

		for (int i = 0; i < objs.size(); i++) {

			CurewellOrderdetail temporder = objs.get(i);
			Double totalCostProduct = 0.0;
			totalCostProduct = totalCostProduct + (temporder.getProductprice() * temporder.getQuantity());
			objs.get(i).setTotalpricebeforetax(totalCostProduct);
			Double calctotalAmountafterTax = 0.0;
			calctotalAmountafterTax = calctotalAmountafterTax
					+ ((temporder.getProductprice() * temporder.getQuantity()))
					+ (((temporder.getProductprice() * temporder.getQuantity())) * (temporder.getCgstpercent()) / 100)
					+ (((temporder.getProductprice() * temporder.getQuantity())) * (temporder.getSgstpercent()) / 100);
			objs.get(i).setTotalpriceaftertax(calctotalAmountafterTax);
			totalorderAmount = totalorderAmount + calctotalAmountafterTax;
		}
		invoice.setOutstandingamount(totalorderAmount - invoice.getAdvancepaid());

		CurewellOrderMaster invoicedata = orderRepo.save(invoice);
		LOGGER.info("INvoiceOrder Created for OrderId" + invoicedata.getOrderid() );
		List<CurewellOrderdetail> orders = new ArrayList<>(invoicedata.getOrderdetails());
		for (int i = 0; i < orders.size(); i++) {
			CurewellInvoiceOrderVO records = new CurewellInvoiceOrderVO();
			records.setOrderid(invoicedata.getOrderid());
			records.setInvoicenumber(invoicedata.getInvoicenumber());
			records.setInvoicedate(invoicedata.getInvoicedate());
			records.setPonumber(invoicedata.getPonumber());
			records.setPodate(invoicedata.getPodate());
			records.setPartytobill(invoicedata.getPartytobill());
			records.setPartyaddress(invoicedata.getPartyaddress());
			records.setPartygstn(invoicedata.getPartygstn());
			records.setPartystate(invoicedata.getPartystate());
			records.setStatecode(invoicedata.getStatecode());
			records.setAdvancepaid(invoicedata.getAdvancepaid());
			records.setOutstandingamount(invoicedata.getOutstandingamount());
			records.setSupplyplace(invoicedata.getSupplyplace());
			records.setProductcode(orders.get(i).getProductcode());
			records.setQuantity(orders.get(i).getQuantity());
			records.setProductprice(orders.get(i).getProductprice());
			records.setCgstpercent(orders.get(i).getCgstpercent());
			records.setSgstpercent(orders.get(i).getSgstpercent());
			records.setTotalpricebeforetax(orders.get(i).getTotalpricebeforetax());
			records.setTotalpriceaftertax(orders.get(i).getTotalpriceaftertax());
			records.setShiptopartyname(invoicedata.getShiptopartyname());
			vo.add(records);
		}

		return vo;

	}

	@Override
	public List<CurewellInvoiceOrderVO> saveCurewellInvoiceOrderAdd(Long id, CurewellOrderdetail orderitem) {
		CurewellOrderMaster invoicedata = orderRepo.findById(id).get();
		List<CurewellInvoiceOrderVO> vo = new ArrayList<CurewellInvoiceOrderVO>();

		if (invoicedata != null && orderitem != null)
		{
			Double totalorderAmount = 0.0;


			CurewellOrderdetail temporder = orderitem;
			Double totalCostProduct = 0.0;
			totalCostProduct = totalCostProduct + (temporder.getProductprice() * temporder.getQuantity());
			orderitem.setTotalpricebeforetax(totalCostProduct);
			Double calctotalAmountafterTax = 0.0;
			calctotalAmountafterTax = calctotalAmountafterTax
					+ ((temporder.getProductprice() * temporder.getQuantity()))
					+ (((temporder.getProductprice() * temporder.getQuantity())) * (temporder.getCgstpercent()) / 100)
					+ (((temporder.getProductprice() * temporder.getQuantity())) * (temporder.getSgstpercent()) / 100);
			orderitem.setTotalpriceaftertax(calctotalAmountafterTax);
			totalorderAmount = (invoicedata.getOutstandingamount() + calctotalAmountafterTax);
			invoicedata.setOutstandingamount(totalorderAmount);
			
			LOGGER.info("INvoiceOrder Adding Item for OrderId" + invoicedata.getOrderid());
			
			invoicedata.addOrderDetails(orderitem);
			
			CurewellOrderMaster invoicedataUpdated = orderRepo.save(invoicedata);

			List<CurewellOrderdetail> orders = new ArrayList<>(invoicedataUpdated.getOrderdetails());
			for (int i = 0; i < orders.size(); i++) {
				CurewellInvoiceOrderVO records = new CurewellInvoiceOrderVO();
				records.setOrderid(invoicedata.getOrderid());
				records.setInvoicenumber(invoicedata.getInvoicenumber());
				records.setInvoicedate(invoicedata.getInvoicedate());
				records.setPonumber(invoicedata.getPonumber());
				records.setPodate(invoicedata.getPodate());
				records.setPartytobill(invoicedata.getPartytobill());
				records.setPartyaddress(invoicedata.getPartyaddress());
				records.setPartygstn(invoicedata.getPartygstn());
				records.setPartystate(invoicedata.getPartystate());
				records.setStatecode(invoicedata.getStatecode());
				records.setAdvancepaid(invoicedata.getAdvancepaid());
				records.setOutstandingamount(invoicedata.getAdvancepaid());
				records.setSupplyplace(invoicedata.getSupplyplace());
				records.setProductcode(orders.get(i).getProductcode());
				records.setQuantity(orders.get(i).getQuantity());
				records.setProductprice(orders.get(i).getProductprice());
				records.setCgstpercent(orders.get(i).getCgstpercent());
				records.setSgstpercent(orders.get(i).getSgstpercent());
				records.setTotalpricebeforetax(orders.get(i).getTotalpricebeforetax());
				records.setTotalpriceaftertax(orders.get(i).getTotalpriceaftertax());
				records.setShiptopartyname(invoicedata.getShiptopartyname());
				vo.add(records);
			}

			
		}
	
		return vo;
	}

}
