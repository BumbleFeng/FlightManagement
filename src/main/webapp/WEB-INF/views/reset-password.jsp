<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
</head>
<body>
	<form action="${cp}/resetPassword.htm" method="POST">
		<table>
			<tr>
				<td>Email:</td>
				<td>${email}<input type="hidden" name="email" value="${email}" />
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input id="p1" type="password" name="password1"
					minlength="4" size="30" required="required" oninput="validityPwd()" /></td>
			</tr>
			<tr>
				<td>Re-enter:</td>
				<td><input id="p2" type="password" name="password"
					 minlength="4" size="30" required="required"
					oninput="validityPwd()" /></td>
			</tr>
			<tr>

				<td colspan="2"><input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" /><input
					type="submit" value="Sumbit" /></td>
			</tr>
		</table>
	</form>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		var validityPwd = function() {
			var pw1 = document.getElementById('p1');
			var pw2 = document.getElementById('p2');
			if (pw1.value != pw2.value) {
				pw2.setCustomValidity('Password Inconsistent');
			} else {
				pw2.setCustomValidity('');
			}
		}
	</script>
</body>
</html>