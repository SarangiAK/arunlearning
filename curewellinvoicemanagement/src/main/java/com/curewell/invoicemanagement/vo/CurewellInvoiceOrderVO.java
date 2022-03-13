
package com.curewell.invoicemanagement.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

 
public class CurewellInvoiceOrderVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private long orderid;
	private String invoicenumber;
	@Temporal(TemporalType.DATE)
	private Date invoicedate;
	private String ponumber;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date podate;
	private String partytobill;
	private String partyaddress;
	private String partygstn;
	private String partystate;
	private String statecode;
	private Double advancepaid;
	private Double outstandingamount;
	private String supplyplace;
	private String shiptopartyname;
	private String productcode;
	private int quantity;
	private double productprice;
	private int cgstpercent;
	private int sgstpercent;
	private double totalpricebeforetax;
	private double totalpriceaftertax;
	public long getOrderid() {
		return orderid;
	}
	public String getInvoicenumber() {
		return invoicenumber;
	}
	public Date getInvoicedate() {
		return invoicedate;
	}
	public String getPonumber() {
		return ponumber;
	}
	public Date getPodate() {
		return podate;
	}
	public String getPartytobill() {
		return partytobill;
	}
	public String getPartyaddress() {
		return partyaddress;
	}
	public String getPartygstn() {
		return partygstn;
	}
	public String getPartystate() {
		return partystate;
	}
	public String getStatecode() {
		return statecode;
	}
	public Double getAdvancepaid() {
		return advancepaid;
	}
	public Double getOutstandingamount() {
		return outstandingamount;
	}
	public String getSupplyplace() {
		return supplyplace;
	}
	public String getProductcode() {
		return productcode;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getProductprice() {
		return productprice;
	}
	public int getCgstpercent() {
		return cgstpercent;
	}
	public int getSgstpercent() {
		return sgstpercent;
	}
	public double getTotalpricebeforetax() {
		return totalpricebeforetax;
	}
	public double getTotalpriceaftertax() {
		return totalpriceaftertax;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public void setInvoicenumber(String invoicenumber) {
		this.invoicenumber = invoicenumber;
	}
	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}
	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}
	public void setPodate(Date podate) {
		this.podate = podate;
	}
	public void setPartytobill(String partytobill) {
		this.partytobill = partytobill;
	}
	public void setPartyaddress(String partyaddress) {
		this.partyaddress = partyaddress;
	}
	public void setPartygstn(String partygstn) {
		this.partygstn = partygstn;
	}
	public void setPartystate(String partystate) {
		this.partystate = partystate;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public void setAdvancepaid(Double advancepaid) {
		this.advancepaid = advancepaid;
	}
	public void setOutstandingamount(Double outstandingamount) {
		this.outstandingamount = outstandingamount;
	}
	public void setSupplyplace(String supplyplace) {
		this.supplyplace = supplyplace;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}
	public void setCgstpercent(int cgstpercent) {
		this.cgstpercent = cgstpercent;
	}
	public void setSgstpercent(int sgstpercent) {
		this.sgstpercent = sgstpercent;
	}
	public void setTotalpricebeforetax(double totalpricebeforetax) {
		this.totalpricebeforetax = totalpricebeforetax;
	}
	public void setTotalpriceaftertax(double totalpriceaftertax) {
		this.totalpriceaftertax = totalpriceaftertax;
	}
	public String getShiptopartyname() {
		return shiptopartyname;
	}
	public void setShiptopartyname(String shiptopartyname) {
		this.shiptopartyname = shiptopartyname;
	}
	@Override
	public String toString() {
		return "CurewellInvoiceOrderVO [orderid=" + orderid + ", invoicenumber=" + invoicenumber + ", invoicedate="
				+ invoicedate + ", ponumber=" + ponumber + ", podate=" + podate + ", partytobill=" + partytobill
				+ ", partyaddress=" + partyaddress + ", partygstn=" + partygstn + ", partystate=" + partystate
				+ ", statecode=" + statecode + ", advancepaid=" + advancepaid + ", outstandingamount="
				+ outstandingamount + ", supplyplace=" + supplyplace + ", productcode=" + productcode + ", quantity="
				+ quantity + ", productprice=" + productprice + ", cgstpercent=" + cgstpercent + ", sgstpercent="
				+ sgstpercent + ", totalpricebeforetax=" + totalpricebeforetax + ", totalpriceaftertax="
				+ totalpriceaftertax + "]";
	}



}
