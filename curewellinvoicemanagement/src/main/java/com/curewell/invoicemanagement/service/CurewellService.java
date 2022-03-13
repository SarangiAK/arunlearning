package com.curewell.invoicemanagement.service;

import java.util.List;

import com.curewell.invoicemanagement.entities.CurewellOrderMaster;
import com.curewell.invoicemanagement.entities.CurewellOrderdetail;
import com.curewell.invoicemanagement.vo.CurewellInvoiceOrderVO;

public interface CurewellService {

	public List<CurewellInvoiceOrderVO> getInvoiceByOrderId(Long orderid);
	public List<CurewellInvoiceOrderVO> saveCurewellInvoiceOrder(CurewellOrderMaster invoice);
	public List<CurewellInvoiceOrderVO> saveCurewellInvoiceOrderAdd(Long id,CurewellOrderdetail orderitem);
	
}
