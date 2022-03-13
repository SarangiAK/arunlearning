package com.curewell.invoicemanagement.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="curewellordermaster")

public class CurewellOrderMaster implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderid;
	private String invoicenumber;
	@Temporal(TemporalType.DATE)
	private Date invoicedate;
	private String ponumber;
	@Temporal(TemporalType.DATE)
	private Date podate;
	private String partytobill;
	private String partyaddress;
	private String partygstn;
	private String partystate;
	private String statecode;
	private Double advancepaid;
	private Double outstandingamount;
	private String shiptopartyname;
	private String supplyplace;
	@OneToMany(mappedBy = "curewellordermaster" ,fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CurewellOrderdetail> orderdetails;

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public String getInvoicenumber() {
		return invoicenumber;
	}

	public void setInvoicenumber(String invoicenumber) {
		this.invoicenumber = invoicenumber;
	}

	public Date getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}

	public String getPonumber() {
		return ponumber;
	}

	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}

	public Date getPodate() {
		return podate;
	}

	public void setPodate(Date podate) {
		this.podate = podate;
	}

	public String getPartytobill() {
		return partytobill;
	}

	public void setPartytobill(String partytobill) {
		this.partytobill = partytobill;
	}

	public String getPartyaddress() {
		return partyaddress;
	}

	public void setPartyaddress(String partyaddress) {
		this.partyaddress = partyaddress;
	}

	public String getPartygstn() {
		return partygstn;
	}

	public void setPartygstn(String partygstn) {
		this.partygstn = partygstn;
	}

	public String getPartystate() {
		return partystate;
	}

	public void setPartystate(String partystate) {
		this.partystate = partystate;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public Double getAdvancepaid() {
		return advancepaid;
	}

	public void setAdvancepaid(Double advancepaid) {
		this.advancepaid = advancepaid;
	}

	public String getSupplyplace() {
		return supplyplace;
	}

	public void setSupplyplace(String supplyplace) {
		this.supplyplace = supplyplace;
	}

	public Double getOutstandingamount() {
		return outstandingamount;
	}

	public void setOutstandingamount(Double outstandingamount) {
		this.outstandingamount = outstandingamount;
	}
	@JsonManagedReference
	public List<CurewellOrderdetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<CurewellOrderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public void addOrderDetails(CurewellOrderdetail orderdetail) {
		if (orderdetail != null) {
			if (orderdetails == null) {
				orderdetails = new ArrayList<>();
			}
			orderdetail.setOrdermaster(this);
			orderdetails.add(orderdetail);
		}
	}

	public String getShiptopartyname() {
		return shiptopartyname;
	}

	public void setShiptopartyname(String shiptopartyname) {
		this.shiptopartyname = shiptopartyname;
	}

	@Override
	public String toString() {
		return "CurewellOrderMaster [orderid=" + orderid + ", invoicenumber=" + invoicenumber + ", invoicedate="
				+ invoicedate + ", ponumber=" + ponumber + ", podate=" + podate + ", partytobill=" + partytobill
				+ ", partyaddress=" + partyaddress + ", partygstn=" + partygstn + ", partystate=" + partystate
				+ ", statecode=" + statecode + ", advancepaid=" + advancepaid + ", outstandingamount="
				+ outstandingamount + ", supplyplace=" + supplyplace +  "]";
	}

}
