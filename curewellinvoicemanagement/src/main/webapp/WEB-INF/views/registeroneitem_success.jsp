<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Invoice Order Entry Success</title>
<style type="text/css">

body
        {
           background-color:powderblue;
            background-size: 100% 100%;
            margin: 0;
            padding: 0;
        }
        
	label {
		display: inline-block;
		width: 200px;
		margin: 5px;
		text-align: left;
	}
	input[type=text], input[type=password], select {
		width: 200px;	
	}
	input[type=radio] {
		display: inline-block;
		margin-left: 45px;
	}
	input[type=checkbox] {
		display: inline-block;
		margin-right: 190px;
	}	
	
	button {
		padding: 5px;
		margin: 5px;
	}
	
	table, th, td {
  border: 1px solid black;
}

table {
  width: 100%;
}
</style>

</head>
<body>
	<div align="left">
		<h2>One Item Added SuccessFully!</h2>
        <form:form action="${pageContext.servletContext.contextPath}/curewell/registerinvoiceaddmore/${curewellinvoiceordervo.orderid}" method="post" modelAttribute="curewellinvoiceordervo">
 <table>
  <tr>
    <th>Order-Id</th>
    <th>Product Name</th>
    <th>Product Qty</th>
    <th>Unit Price</th>
    <th>CGST/IGST</th>
    <th>SGST</th>
    <th>Amount Without Tax</th>
    <th>Amount With Tax</th>

  </tr>
  <tr>
    <td><span>${curewellinvoiceordervo.orderid}</span></td>
    <td><span>${curewellinvoiceordervo.productcode}</span></td>
    <td><span>${curewellinvoiceordervo.quantity}</span></td>
    <td><span>${curewellinvoiceordervo.productprice}</span></td>
    <td><span>${curewellinvoiceordervo.cgstpercent}</span></td>
    <td><span>${curewellinvoiceordervo.sgstpercent}</span></td>
    <td><span>${curewellinvoiceordervo.totalpricebeforetax}</span></td>
    <td><span>${curewellinvoiceordervo.totalpriceaftertax}</span></td>
    
  </tr>
  <tr>
  		<form:button name="additem" >Add More Item</form:button>
		<form:button name="generateinvoice">Generate Invoice</form:button>
		<form:button name="gotoHome">Home</form:button>
		<form:button name="logout">Logout</form:button>
  </tr>
 </table>
 </form:form>
	</div>
	</body>
</html>