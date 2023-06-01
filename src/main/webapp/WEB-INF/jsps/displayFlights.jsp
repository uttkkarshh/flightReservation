<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored = "false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>flights</h2>
<table>
<tr><th>Airlines</th>
<th>Departure City</th>
<th>arrival city</th>
<th>Departure Time</th></tr>

<tr>


<c:forEach items="${flights}" var="flight">
<tr>
<td>${flight.operatingAirlines}</td>
<td>${flight.departureCity}</td>
<td>${flight.arrivalCity}</td>

<td><a href="showCompleteReservation?flightId=${flight.id}">select</a> </td>
</tr>
</c:forEach>

</table>
</body>
</html>