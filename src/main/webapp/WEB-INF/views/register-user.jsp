<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<font color="red">${errorMessage}</font>
	<form action="${cp}/registerUser.htm" method="POST">
		<table>
			<tr>
				<td>User Name:</td>
				<td><input type="text" name="username" minlength="3" size="30"
					required="required" /> <b style="color: red"></b></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input id="p1" type="password" name="password1" minlength="4" size="30"
					required="required" oninput="validityPwd()"/></td>
			</tr>
			<tr>
				<td>Re-enter:</td>
				<td><input id="p2" type="password" name="password" minlength="4" size="30"
					required="required" oninput="validityPwd()"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input id="email" type="email" name="email" minlength="10" size="30"
					required="required" /> <b id="r" style="color: red"></b></td>
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

				<td colspan="2"><input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" /><input
					type="submit" value="Register" /></td>
			</tr>
		</table>
	</form>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$("input:first").keyup(function() {
				txt = $("input:first").val();
				if (txt.length >= 3) {
					$.ajax({
						type : "POST",
						url : "checkUsername.htm",
						data : {
							username : txt,
							"${_csrf.parameterName}" : "${_csrf.token}"
						},
						success : function(result) {
							$("b:first").html(result);
						}
					});
				}
			});
		});
	</script>
	<script>
		$(document).ready(function() {
			$("#email").keyup(function() {
				txt = $("#email").val();
				if (txt.length >= 10) {
					$.ajax({
						type : "POST",
						url : "checkEmail.htm",
						data : {
							email : txt,
							"${_csrf.parameterName}" : "${_csrf.token}"
						},
						success : function(result) {
							$("#r").html(result);
						}
					});
				}
			});
		});
	</script>
	<script>
	var validityPwd = function(){
	    var pw1 = document.getElementById('p1');
	    var pw2 = document.getElementById('p2');
	    if(pw1.value != pw2.value){
	        pw2.setCustomValidity('Password Inconsistent');
	    }else{
	        pw2.setCustomValidity('');
	    }
	}
	</script>
</body>
</html>