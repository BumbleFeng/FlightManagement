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
	width: 510px;
	display: block;
}

.center {
	text-align: center;
}

label {
	display: inline-block;
	width: 140px;
	text-align: right;
}
</style>
<title>Sales</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			From : ${airlineCode} <a href="${cp}/logout" >Logout</a>
		</div>
		<h1>Sales</h1>
		<div class="span-24">
			<table>
				<tr>
					<td>Call Sign</td>
					<td>Date</td>
					<td>Firstclass Remain</td>
					<td>Firstclass Price</td>
					<td>Business Remain</td>
					<td>Business Price</td>
					<td>Economy Remain</td>
					<td>Economy Price</td>
					<td>Sales</td>
				</tr>
				<c:forEach items="${flyDuties}" var="flyDuty">
					<tr>
						<td>${flyDuty.flight.callSign}</td>
						<td>${flyDuty.date}</td>
						<td>${flyDuty.firstclassRemain} <i>/</i> ${flyDuty.aircraft.firstclassSeats}</td>
						<td><input type="text" name="mileage" required="required"
							onkeyup="this.value=this.value.replace(/\D/g,'')"
							value="${flyDuty.firstclassPrice}" /></td>
						<td>${flyDuty.businessRemain} <i>/</i> ${flyDuty.aircraft.businessSeats}</td>
						<td><input type="text" name="mileage" required="required"
							onkeyup="this.value=this.value.replace(/\D/g,'')"
							value="${flyDuty.businessPrice}"></td>
						<td>${flyDuty.economyRemain} <i>/</i> ${flyDuty.aircraft.economicSeats}</td>
						<td><input type="text" name="mileage" required="required"
							onkeyup="this.value=this.value.replace(/\D/g,'')"
							value="${flyDuty.economyPrice}"></td>
						<td>${flyDuty.sales}</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div>
				<a style="float: left" href="airline.htm">[Go Back]</a> <input
					style="float: right" type="submit" value="submit">
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$("input:submit").click(function() {
				var arr = new Array;
				$("table input").each(function() {
					arr.push($(this).val());
				});
				$.ajax({
					type : "POST",
					url : "updatePrices.htm",
					data : {
						prices : JSON.stringify(arr),
						"${_csrf.parameterName}" : "${_csrf.token}"
					},
					success : function(result) {
						window.location.reload();
					}
				});

			});
		});
	</script>
</body>
</html>