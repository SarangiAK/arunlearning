<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Entry Form</title>
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
  border: 0px solid black;
}

table {
  width: 100%;
}
</style>
</head>
</style>
</head>
</style>
</head>


<body>
	<div align="center">
		<h2>Curewell Order Additional Item Entry</h2>
		<script type = "text/javascript">
   <!--
   function gohome()
   {
   return true;
   }
      function validate() {

   
         
        var regu = /^[1-9]\d*$/;
            
        if( document.forms[0].quantity.value == "" || isNaN(document.forms[0].quantity.value) || !regu.test(document.forms[0].quantity.value)) {
            alert( "Please provide the Quantity in positive numbers!" );
            document.forms[0].quantity.focus() ;
            return false;
         }
         var regu =/^[1-9]\d*(\.[0-9]\d+)?$/;
         
        if( document.forms[0].productprice.value == "" || isNaN(document.forms[0].productprice.value) || !regu.test(document.forms[0].productprice.value) ) {
            alert( "Please provide the Price in Postive Numeric XXX.XX format!" );
            document.forms[0].productprice.focus() ;
            return false;
         }
         var regu = /^[0-9]\d*$/;
         if( document.forms[0].cgstpercent.value == "" || isNaN(document.forms[0].cgstpercent.value) || !regu.test(document.forms[0].cgstpercent.value)) {
            alert( "Please provide the Central GST  in numbers!" );
            document.forms[0].cgstpercent.focus() ;
            return false;
         }
         if( document.forms[0].sgstpercent.value == "" || isNaN(document.forms[0].sgstpercent.value) || !regu.test(document.forms[0].cgstpercent.value) ) {
            alert( "Please provide the State GST  in numbers!" );
            document.forms[0].sgstpercent.focus() ;
            return false;
         } 
         
         return( true );
      }
   //-->
</script>
		<form:form id = "invoiceForm2" action="${pageContext.servletContext.contextPath}/curewell/registerinvoiceaddmore/registerinvoicepostitem" method="post" modelAttribute="curewellinvoiceordervo" >
		<table>
		<tr>
			<form:label path="orderid">Order ID</form:label>
			<form:input path="orderid" value="${orderIdValue}" readonly="readonly"/>	
			<form:label path="productcode">Product Name:</form:label>
			<form:select path="productcode" items="${productcodeList}" /><br/>
		</tr>	
			
			<tr>	
			<form:label path="quantity">No Of Quantity</form:label>
			<form:input path="quantity" value="1"/>
			<form:label path="productprice">Product Price</form:label>
			<form:input path="productprice"/><br/>
            </tr>
            <tr>
			<form:label path="cgstpercent">C-GST Percent</form:label>
			<form:input path="cgstpercent"/>
			<form:label path="sgstpercent">S-GST Percent</form:label>
			<form:input path="sgstpercent"/><br/>
			</tr>

			</table>
			<form:button id="additem" name="additem" onclick="return(validate());" >Save Item </form:button>
			<form:button id="gotoHome" name="gotoHome" onclick="gohome()">Home </form:button>
			<form:button id="logout" name="logout" onclick="gohome()" >Logout </form:button>
		</form:form>
	</div>
</body>
</html>