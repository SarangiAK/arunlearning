<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curewell Login Page</title>
<style type="text/css">

body
        {
            background:url('/resources/images/curewell_logo.jpg') no-repeat center center fixed;
            background-color:powderblue;
            background-size: 70% 70%;
            margin: 0;
            padding: 0;
        }

	label {
		display: inline-block;
		width: 75px;
		text-align: left;
		color: blue;
		font-weight: bold;
		
	}
	input[type=text], input[type=password], select {
		width: 100px;	
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
		color: green;
	}

</style>
</head>

<body  >
	<div align="center">
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
		<form:form action="/curewell/login" method="post" modelAttribute="user">
			<form:label path="email" color="BLUE" >User Id:</form:label>
			<form:input path="email"/>
			<form:label path="password" color="BLUE">Password:</form:label>
			<form:password path="password"/><form:button>Login</form:button> <br/>		

		</form:form>
	</div>
</body>
</html>