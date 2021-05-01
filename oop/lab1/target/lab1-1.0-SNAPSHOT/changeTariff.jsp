<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>unpaidList</title>
</head>
<body>
<p>${yourTariff}</p>
<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>name</th>
        <th>price</th>
        <th>connect</th>
    </tr>
    <c:forEach items="${tariffList}" var="tariff" >
        <tr>
            <td>${tariff.nametariff}</td>
            <td>${tariff.price}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
