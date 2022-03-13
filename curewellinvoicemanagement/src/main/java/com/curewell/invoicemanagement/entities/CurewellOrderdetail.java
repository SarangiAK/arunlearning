package com.curewell.invoicemanagement.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CurewellOrderdetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String productcode;
	private int quantity;
	private double productprice;
	private int cgstpercent;
	private int sgstpercent;
	private double totalpricebeforetax;
	private double totalpriceaftertax;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderid")
	
	private CurewellOrderMaster curewellordermaster;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getProductprice() {
		return productprice;
	}

	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}

	public int getCgstpercent() {
		return cgstpercent;
	}

	public void setCgstpercent(int cgstpercent) {
		this.cgstpercent = cgstpercent;
	}

	public int getSgstpercent() {
		return sgstpercent;
	}

	public void setSgstpercent(int sgstpercent) {
		this.sgstpercent = sgstpercent;
	}

	public double getTotalpricebeforetax() {
		return totalpricebeforetax;
	}

	public void setTotalpricebeforetax(double totalpricebeforetax) {
		this.totalpricebeforetax = totalpricebeforetax;
	}

	public double getTotalpriceaftertax() {
		return totalpriceaftertax;
	}

	public void setTotalpriceaftertax(double totalpriceaftertax) {
		this.totalpriceaftertax = totalpriceaftertax;
	}
	@JsonBackReference
	public CurewellOrderMaster getOrdermaster() {
		return curewellordermaster;
	}

	public void setOrdermaster(CurewellOrderMaster curewellordermaster) {
		this.curewellordermaster = curewellordermaster;
	}
	
	  @Override public String toString() { return "CurewellOrderDetail [id=" + id +
	  ", productcode=" + productcode + ", quantity=" +
	  quantity + ", productprice=" + productprice + ", cgstpercent=" + cgstpercent
	  + ", sgstpercent=" + sgstpercent + ", totalpricebeforetax=" +
	  totalpricebeforetax + ", totalpriceaftertax=" + totalpriceaftertax + "]"; }
	 

}
