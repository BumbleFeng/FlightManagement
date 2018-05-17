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
	width: 390px;
	display: block;
}

.center {
	text-align: center;
}

label {
	display: inline-block;
	width: 80px;
	text-align: right;
}
</style>
<title>Add Airport</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			<a href="${cp}/logout" >Logout</a>
		</div>
		<h1>Add Airport</h1>
		<div class="span-20">
			<form action="addAirport.htm" method="post">
				<div class="block">
					<label>Airport Code:</label> <input type="text" name="airportCode"
						maxlength="3" required="required" />&nbsp;<b style="color: red"></b>
				</div>
				<div class="block">
					<label>Airport Name:</label> <input type="text" name="airportName"
						required="required" />
				</div>
				<div class="block">
					<label>City:</label> <input type="text" name="city"
						required="required" />
				</div>
				<div class="block">
					<label>Country:</label> <input type="text" name="country"
						required="required" />
				</div>
				<div class="block">
					<label>Time Zone:</label> <input type="text" name="timeZone"
						required="required" placeholder="±HH:MM" />
				</div>
				<div class="block">
					<label>Terminal:</label> <input type="text" name="terminals"
						required="required" placeholder="A,B,C" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="block center">
					<input type="submit" value="Add">
				</div>
				<p></p>
			</form>
		</div>
		<br />
		<div class="span-20">
			<table>
				<tr>
					<td>Code</td>
					<td>Name</td>
					<td>City</td>
					<td>Country</td>
					<td>Time Zone</td>
					<td>Terminals</td>
				</tr>
				<c:forEach items="${airports}" var="airport">
					<tr>
						<td>${airport.airportCode}</td>
						<td>${airport.airportName}</td>
						<td>${airport.city}</td>
						<td>${airport.country}</td>
						<td>${airport.timeZone/60}</td>
						<td><c:forEach items="${airport.terminals}" var="terminal">
						${terminal.terminalCode}&nbsp;
						</c:forEach></td>
					</tr>
				</c:forEach>
			</table>
			<a href="admin.htm">[Go Back]</a>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$("input:first").keyup(function() {
				txt = $("input:first").val();
				if (txt.length == 3) {
					$.ajax({
						type : "POST",
						url : "checkAirport.htm",
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