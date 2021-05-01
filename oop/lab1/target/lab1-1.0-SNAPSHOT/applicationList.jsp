<%--
  Created by IntelliJ IDEA.
  User: Анастасия
  Date: 01.04.2021
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>unpaidList</title>
</head>
<body>
<div style="padding: 5px;">
    <a href="${pageContext.request.contextPath}/unpaidList.jsp">List of unpaid invoices</a>
    |
    <a href="${pageContext.request.contextPath}/applicationList.jsp">List of applications</a>
</div>
<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>surname</th>
        <th>name</th>
        <th>phoneNumber</th>
        <th>Block</th>
    </tr>
    <c:forEach items="${notuserList}" var="notuser" >
        <tr>
            <td>${notuser.surname}</td>
            <td>${notuser.name}</td>
            <td>${notuser.phoneNumber}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
