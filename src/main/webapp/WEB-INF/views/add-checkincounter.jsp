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
	width: 530px;
	display: block;
}

.center {
	text-align: center;
}

label {
	display: inline-block;
	width: 150px;
	text-align: right;
}
</style>
<title>Add CheckinCounter</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			<a href="${cp}/logout" >Logout</a>
		</div>
		<h1>Add CheckinCounter</h1>
		<div class="span-20">
			<form action="addCheckinCounter.htm" method="post">
				<div ng-app="myApp" ng-controller="myCtrl">
					<div>
						<label>Select Airline:</label> <select name="airlineCode"
							ng-model="selectedAirline"
							ng-options="x.airlineCode for x in airlineList">
						</select> {{selectedAirline.airlineName}}
					</div>
					<div>
						<label>Select Airport:</label> <select name="airportCode"
							ng-model="selectedAirport"
							ng-options="y.airportCode for y in airportList">
						</select> {{selectedAirport.city}}
					</div>
					<div>
						<label>Select Terminal:</label> <select name="terminalCode"
							ng-model="selectedTerminal"
							ng-options="z.terminalCode for z in selectedAirport.terminals">
						</select>
					</div>
				</div>
				<div class="block">
					<label>CheckinCounter Code:</label> <input type="text"
						name="checkinCounterCode" required="required" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="block center">
					<input type="submit" value="Add">
				</div>
				<p></p>
			</form>
		</div>
		<div class="span-10">
			<table>
				<tr>
					<td>Airline Code</td>
					<td>Airport Code</td>
					<td>Terminal Code</td>
					<td>CheckinCounter Code</td>
				</tr>
				<c:forEach items="${checkinCounters}" var="checkinCounter">
					<tr>
						<td>${checkinCounter.airline.airlineCode}</td>
						<td>${checkinCounter.terminal.airport.airportCode}</td>
						<td>${checkinCounter.terminal.terminalCode}</td>
						<td>${checkinCounter.checkinCounterCode}</td>
					</tr>
				</c:forEach>
			</table>
			<a href="admin.htm">[Go Back]</a>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
	<script>
		var app = angular.module('myApp', []);
		app.controller('myCtrl', function($scope, $http) {
			$http.get("airlineList.htm").then(function(response) {
				$scope.airlineList = response.data;
			});
			$http.get("airportList.htm").then(function(response) {
				$scope.airportList = response.data;
			});
		});
	</script>
</body>
</html>