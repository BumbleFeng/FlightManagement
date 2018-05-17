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
<link rel="stylesheet" href="/resources/demos/style.css">
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
<title>Search Flights</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			<c:if test="${user!=null && user.getRole().getId() != 1}">&emsp;<a href="${cp}/logout" >Logout</a></c:if>
			<c:if test="${user.getRole().getId() == 2}">&emsp;<a href="${cp}/user/addPassenger.htm" >Add Passenger</a>&emsp;<a href="${cp}/user/orders.htm" >Order List</a></c:if>
			<c:if test="${user.getRole().getId() == 1}">&emsp;<a href="${cp}/resendEmail.htm" >Please activate your account</a></c:if>
			<c:if test="${user==null}">&emsp;<a href="${cp}/login" >Login</a>&emsp;<a href="${cp}/registerUser.htm" >Register User</a></c:if>
			&emsp;<a href="${cp}/forgetPassword.htm" >Reset password</a>
		</div>
		<h1>Search Flights</h1>
		<div class="span-20">
			<form action="searchFlights.htm" method="post">
				<div class="block">
					<label>Departure City:</label> <input type="text"
						name="departureCity" class="city1" required="required">
					<div id="context1"
						style="background-color: white; border: 1px solid black; width: 127px; position: absolute; top: 144px; left: 388px; display: none">
					</div>
				</div>
				<div class="block">
					<label>Arrival City:</label> <input type="text" name="arrivalCity"
						class="city2" required="required">
					<div id="context2"
						style="background-color: white; border: 1px solid black; width: 127px; position: absolute; top: 172px; left: 388px; display: none">
					</div>
				</div>
				<div class="block">
					<label>Date :</label> <input type="text" name="date"
						id="datepicker" required="required">
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="block center">
					<input type="submit" value="Search">
				</div>
				<p></p>
			</form>
		</div>
		<div class="span-24">
			<table>
				<tr>
					<td>Call Sign</td>
					<td>Date</td>
					<td>Departure Airport</td>
					<td>Arrival Airport</td>
					<td>Departure Time</td>
					<td>Arrival Time</td>
					<td>Floor Price</td>
				</tr>
				<c:forEach items="${flyDuties}" var="flyDuty">
					<tr>
						<td onclick="location.href='${cp}/bookTickets.htm?id=${flyDuty.id}';">${flyDuty.flight.callSign}</td>
						<td onclick="location.href='${cp}/bookTickets.htm?id=${flyDuty.id}';">${flyDuty.date}</td>
						<td onclick="location.href='${cp}/bookTickets.htm?id=${flyDuty.id}';">${flyDuty.departureTerminal.airport.airportCode}&emsp;${flyDuty.departureTerminal.terminalCode}</td>
						<td onclick="location.href='${cp}/bookTickets.htm?id=${flyDuty.id}';">${flyDuty.arrivalTerminal.airport.airportCode}&emsp;${flyDuty.arrivalTerminal.terminalCode}</td>
						<td onclick="location.href='${cp}/bookTickets.htm?id=${flyDuty.id}';">${flyDuty.actualDeparture}</td>
						<td onclick="location.href='${cp}/bookTickets.htm?id=${flyDuty.id}';">${flyDuty.actualArrival}</td>
						<td onclick="location.href='${cp}/bookTickets.htm?id=${flyDuty.id}';">${flyDuty.economyPrice}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#datepicker").datepicker({
				dateFormat : "yy-mm-dd",
				minDate : 0,
				numberOfMonths : 2,
				changeMonth : true,
				changeYear : true,
				showButtonPanel : true
			});
		});
	</script>
	<script>
    function changeBackColor_over(div){
        $(div).css("background-color","#CCCCCC");
    }
    function changeBackColor_out(div){
        $(div).css("background-color","");
    }
    function setSearch_onclick1(div){
        $(".city1").val(div.innerText);
        $("#context1").css("display","none");
    }
    function setSearch_onclick2(div){
        $(".city2").val(div.innerText);
        $("#context2").css("display","none");
    }
    
	$(".city1").keyup(function(){
        var content=$(this).val();
        if(content==""){
            $("#context1").css("display","none");
            return ;
        }
        $.ajax({
            type:"post",
            url:"cityList.htm",
            data:{city : content,
				"${_csrf.parameterName}" : "${_csrf.token}"},
            success:function(data){
                var res=data.split(",");
                var html="";
                for(var i=0;i<res.length;i++){
                    html+="<div onclick='setSearch_onclick1(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"+res[i]+"</div>";
                }
                $("#context1").html(html);
                $("#context1").css("display","block");
                
            }
        });

    });
	$(".city2").keyup(function(){
        var content=$(this).val();
        if(content==""){
            $("#context2").css("display","none");
            return ;
        }
        $.ajax({
            type:"post",
            url:"cityList.htm",
            data:{city : content,
				"${_csrf.parameterName}" : "${_csrf.token}"},
            success:function(data){
                var res=data.split(",");
                var html="";
                for(var i=0;i<res.length;i++){
                    html+="<div onclick='setSearch_onclick2(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"+res[i]+"</div>";
                }
                $("#context2").html(html);
                $("#context2").css("display","block");          
            }
        });

    });
	</script>
</body>
</html>