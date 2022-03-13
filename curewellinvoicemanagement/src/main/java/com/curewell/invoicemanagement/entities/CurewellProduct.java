package com.curewell.invoicemanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="curewellproduct")
public class CurewellProduct {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productid;
	private String producthsncode;
	private String producttype;
	private String productdescription;
	private Double procuctcost;
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProducthsncode() {
		return producthsncode;
	}
	public void setProducthsncode(String producthsncode) {
		this.producthsncode = producthsncode;
	}
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	public String getProductdescription() {
		return productdescription;
	}
	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}
	public Double getProcuctcost() {
		return procuctcost;
	}
	public void setProcuctcost(Double procuctcost) {
		this.procuctcost = procuctcost;
	}

}
