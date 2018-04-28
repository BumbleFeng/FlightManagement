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
<title>Airline Page</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			From : ${airlineCode} <a href="${cp}/logout">Logout</a><br/>
			<c:if test="${unsafe== true}">Your password is not safe,please <a
					href="${cp}/forgetPassword.htm">reset the password</a>
			</c:if>
		</div>
		<a href="addAircraft.htm">[Add Aircraft]</a> <br /> <a
			href="addFlight.htm">[Add Flight]</a> <br /> <a
			href="addFlyDuty.htm">[Add FlyDuty]</a> <br /> <a href="sales.htm">[Sales]</a>
	</div>
</body>
</html>