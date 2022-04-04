<%@ page import="java.util.HashMap" %>
<%@ page import="com.example.filtry.Link" %><%--
  Created by IntelliJ IDEA.
  User: matt3
  Date: 31.03.2022
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ol>
    <c:forEach items="${sortedLinks}" var="entry">
        <li>
            value = ${ entry.getUrl() } numbersVisited: ${entry.getTimesVisited()}
        </li>
    </c:forEach>
<ol/>
</body>
</html>
