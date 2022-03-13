<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curewell Order Entry Form</title>
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
		padding: 05px;
		margin: 05px;
	}
	
	table, th, td {
  border: 0px solid black;
}

table {
  width: 100%;
}
</style>
</head>


<body>
	<div align="center">
	

		<h2>Order Invoice Entry</h2>
		
<script type = "text/javascript">
   <!--
      function validate() {
      
     

        if(document.forms[0].getElementById('gotoHome').clicked == true)
         {
           return true;
         }
         
         if(document.forms[0].getElementById('logout').clicked == true)
         {
           return true;
         }
 
         if( document.forms[0].partytobill.value == "" ) {
            alert( "Please provide the Party Name!" );
            document.forms[0].partytobill.focus() ;
            return false;
         }
         
         if( document.forms[0].partyaddress.value == "" ) {
            alert( "Please provide the Party Address!" );
            document.forms[0].partyaddress.focus() ;
            return false;
         }
         if( document.forms[0].partygstn.value == "" ) {
            alert( "Please provide the Party GSTN!" );
            document.forms[0].partygstn.focus() ;
            return false;
         }
         
         if( document.forms[0].partystate.value == "" ) {
            alert( "Please provide the Party State Name!" );
            document.forms[0].partystate.focus() ;
            return false;
         }
         
         if( document.forms[0].statecode.value == "" ) {
            alert( "Please provide the Party State Code!" );
            document.forms[0].statecode.focus() ;
            return false;
         }
         
         if( document.forms[0].advancepaid.value == "" ) {
            alert( "Please provide the Advance Amount(0.00) !" );
            document.forms[0].advancepaid.focus() ;
            return false;
         }
         
         if( document.forms[0].shiptopartyname.value == "" ) {
            alert( "Please provide the Ship To Party Name!" );
            document.forms[0].shiptopartyname.focus() ;
            return false;
         }
                  
        if( document.forms[0].supplyplace.value == "" ) {
            alert( "Please provide the Ship To Party Address!" );
            document.forms[0].supplyplace.focus() ;
            return false;
         }
         var regu = /^[1-9]\d*$/    
        if( document.forms[0].quantity.value == "" || isNaN(document.forms[0].quantity.value) || !regu.test(document.forms[0].quantity.value)) {
            alert( "Please provide the Quantity in positive numbers!" );
            document.forms[0].quantity.focus() ;
            return false;
         }
         var regu =/^[1-9]\d*(\.[0-9]\d+)?$/
         
        if( document.forms[0].productprice.value == "" || isNaN(document.forms[0].productprice.value) || !regu.test(document.forms[0].productprice.value) ) {
            alert( "Please provide the Price in Postive Numeric XXX.XX format!" );
            document.forms[0].productprice.focus() ;
            return false;
         }
         var regu = /^[0-9]\d*$/
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
         
         var datecheck = /^((19|20)\d{2})\-(0[1-9]|1[0-2])\-(0[1-9]|1\d|2\d|3[01])$/;
          if( document.forms[0].podate.value != "" && !datecheck.test(document.forms[0].podate.value) ) {

            alert( "Please provide the Date Format Date YYYY-MM-DD!" );
            document.forms[0].podate.focus() ;
            return false;
           }

         return( true );
      }
   //-->
</script>
		<form:form  name = "invoiceForm" action="${pageContext.servletContext.contextPath}/curewell/registerinvoicepost"  method="post" modelAttribute="curewellinvoiceordervo" onsubmit = "return(validate());">
		   <table>
           <tr>
			<form:label path="ponumber">PO Number:</form:label>
			<form:input path="ponumber"/>
			<form:label path="podate">PO Date (YYYY-MM-DD):</form:label>
			<form:input path="podate"/><br/>
			</tr>
			<tr>
			<form:label path="partytobill">Party To Bill</form:label>
			<form:input path="partytobill"/>
			<form:label path="partyaddress">Party Address:</form:label>
			<form:input path="partyaddress"/><br/>
			</tr>		
            <tr>
			<form:label path="partygstn">Party GSTN Number:</form:label>
			<form:input path="partygstn"/>	
			<form:label path="partystate">Party State Name:</form:label>
			<form:input path="partystate"/><br/>		
            </tr>
            <tr>
			<form:label path="statecode">Party State Code:</form:label>
			<form:input path="statecode"/>
			<form:label path="advancepaid">Advance Amount Paid:</form:label>
			<form:input path="advancepaid"/><br/>		
	        </tr>
	        <tr>
	        <form:label path="shiptopartyname">Ship to Party Name:</form:label>
	        <form:input path="shiptopartyname"/><br/>
			<form:label path="supplyplace">Shipping Address:</form:label>
			<form:input path="supplyplace" />

			</tr>
			<tr>
			<form:label path="productcode">Product Name:</form:label>
			<form:select path="productcode" items="${productcodeList}"/><br/>
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
			<tr>
			<form:button name="addInvoice" >Add Invoice</form:button>
		    <form:button name="gotoHome">Home</form:button>
		    <form:button name="logout">Logout</form:button>
		    </tr>
        </table>
		</form:form>
	</div>
</body>
</html>