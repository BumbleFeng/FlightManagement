<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
</head>
<body>
	<font color="red">${errorMessage}</font>
	<form action="${cp}/forgetPassword.htm" method="POST">
		<table>
			<tr>
				<td>Enter your email:</td>
				<td><input type="email" name="email" size="30"
					required="required" /></td>
			</tr>
			<tr>
				<td colspan="2">
				<label for="captchaCode" class="prompt">Retype the characters from the picture:</label> 
				<%
					Captcha captcha = Captcha.load(request, "CaptchaObject");
  					captcha.setUserInputID("captchaCode");

  					String captchaHtml = captcha.getHtml();
  					out.write(captchaHtml);
				%> 
				<input id="captchaCode" type="text" name="captchaCode" required="required"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>