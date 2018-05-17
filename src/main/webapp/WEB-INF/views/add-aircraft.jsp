<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cp}/resources/blueprint/screen.css"
	type="text/css" media="screen, projection">
<link rel="stylesheet" href="${cp}/resources/blueprint/print.css"
	type="text/css" media="print">
<link rel="stylesheet"
	href="${cp}/resources/blueprint/plugins/fancy-type/screen.css"
	type="text/css" media="screen, projection">
<style type="text/css">
table, th, td {
	border: 1px solid black;
}

.block {
	width: 430px;
	display: block;
}

.center {
	text-align: center;
}

label {
	display: inline-block;
	width: 100px;
	text-align: right;
}
</style>
<title>Add Aircraft</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			From : ${airlineCode} <a href="${cp}/logout" >Logout</a>
		</div>
		<h1>Add Aircraft</h1>
		<div class="span-20">
			<form action="addAircraft.htm" method="post">
				<div class="block">
					<label>Aircraft Code:</label> <input type="text"
						name="aircraftCode" required="required" />&nbsp;<b
						style="color: red"></b>
				</div>
				<div class="block">
					<label>Model:</label> <input type="text" name="model"
						required="required" />
				</div>
				<div class="block">
					<label>Age:</label> <input type="text" name="age"
						required="required"
						onkeyup="this.value=this.value.replace(/\D/g,'')" />
				</div>
				<div class="block">
					<label>Firstclass Seats:</label> <input type="text"
						name="firstclassSeats" required="required"
						onkeyup="this.value=this.value.replace(/\D/g,'')" />
				</div>
				<div class="block">
					<label>Business Seats:</label> <input type="text"
						name="businessSeats" required="required"
						onkeyup="this.value=this.value.replace(/\D/g,'')" />
				</div>
				<div class="block">
					<label>Economic Seats:</label> <input type="text"
						name="economicSeats" required="required"
						onkeyup="this.value=this.value.replace(/\D/g,'')" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="block center">
					<input type="submit" value="Add">
				</div>
				<p></p>
			</form>
		</div>
		<div class="span-15">
			<table>
				<tr>
					<td>Aircraft Code</td>
					<td>Model</td>
					<td>Age</td>
					<td>Firstclass Seats</td>
					<td>Business Seats</td>
					<td>Economic Seats</td>
				</tr>
				<c:forEach items="${aircrafts}" var="aircraft">
					<tr>
						<td>${aircraft.aircraftCode}</td>
						<td>${aircraft.model}</td>
						<td>${aircraft.age}</td>
						<td>${aircraft.firstclassSeats}</td>
						<td>${aircraft.businessSeats}</td>
						<td>${aircraft.economicSeats}</td>
					</tr>
				</c:forEach>
			</table>
			<a href="airline.htm">[Go Back]</a>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$("input:first").keyup(function() {
				txt = $("input:first").val();
				if (txt.length >= 3) {
					$.ajax({
						type : "POST",
						url : "checkAircraft.htm",
						data : {
							code : txt,
							"${_csrf.parameterName}" : "${_csrf.token}"
						},
						success : function(result) {
							$("b").html(result);
						}
					});
				}
			});
		});
	</script>
</body>
</html>