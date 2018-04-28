<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>
	<h1>Error Page</h1>
	<p>${errorMessage}</p>
	<c:if test="${resendLink== true}">
		<form action="${cp}/resendEmail.htm" method="POST">
				<div>
					Email:<input type="email" name="email" size="30" value="${email}"
						required="required" />
				</div>
			<p></p>
			<div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="submit"
					value="Resend Email" />
			</div>
		</form>
	</c:if>
</body>
</html>