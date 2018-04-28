<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<title>Add Airline</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			<a href="${cp}/logout" >Logout</a>
		</div>
		<h1>Add Airline</h1>
		<div class="span-20">
			<form action="addAirline.htm" method="post">
				<div class="block">
					<label>Airline Code:</label> <input type="text" name="airlineCode"
						maxlength="2" required="required" />&nbsp;<b style="color: red"></b>
				</div>
				<div class="block">
					<label>Airline Name:</label> <input type="text" name="airlineName"
						required="required" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="block center">
					<input type="submit" value="Add">
				</div>
				<p></p>
			</form>
		</div>
		<div class="span-5">
			<table>
				<tr>
					<td>Code</td>
					<td>Name</td>
				</tr>
				<c:forEach items="${airlines}" var="airline">
					<tr>
						<td>${airline.airlineCode}</td>
						<td>${airline.airlineName}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="block center">
				<a href="admin.htm">[Go Back]</a>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$("input:first").keyup(function() {
				txt = $("input:first").val();
				if (txt.length == 2) {
					$.ajax({
						type : "POST",
						url : "checkAirline.htm",
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