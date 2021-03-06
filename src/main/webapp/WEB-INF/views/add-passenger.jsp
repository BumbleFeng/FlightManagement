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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
<title>Add Passenger</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			<a href="${cp}/logout" >Logout</a>
		</div>
		<h1>Add Passenger</h1>
		<div class="span-20">
			<form action="addPassenger.htm" method="post">
				<div class="block">
					<label>Real ID:</label> <input type="text" name="realId"
						required="required" />&nbsp;<b style="color: red"></b>
				</div>
				<div class="block">
					<label>First Name:</label> <input type="text" name="firstName"
						required="required" />
				</div>
				<div class="block">
					<label>Last Name:</label> <input type="text" name="lastName"
						required="required" />
				</div>
				<div class="block">
					<label>Birthday:</label> <input type="text" name="birthday" id="datepicker"
						required="required">
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
					<td>Real ID</td>
					<td>First Name</td>
					<td>Last Name</td>
					<td>Birthday</td>
				</tr>
				<c:forEach items="${passengers}" var="passenger">
					<tr>
						<td>${passenger.realId}</td>
						<td>${passenger.firstName}</td>
						<td>${passenger.lastName}</td>
						<td>${passenger.birthday}</td>
					</tr>
				</c:forEach>
			</table>
			<a href="${cp}/searchFlights.htm">[Go Back]</a>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat: "yy-mm-dd",
			numberOfMonths : 2,
			changeMonth : true,
			changeYear : true,
			showButtonPanel : true
		});
	});
</script>
	<script>
		$(document).ready(function() {
			$("input:first").keyup(function() {
				txt = $("input:first").val();
				if (txt.length >= 3) {
					$.ajax({
						type : "POST",
						url : "checkRealId.htm",
						data : {
							realId : txt,
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