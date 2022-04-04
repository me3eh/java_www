<%--
  Created by IntelliJ IDEA.
  User: matt3
  Date: 04.04.2022
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="loginForm" method="post" action="loginServlet">
    Username: <input type="text" name="username"/> <br/>
    Password: <input type="password" name="password"/> <br/>
    Remember me: <input type="checkbox" name="rememberMe" value="Clicked"/> <br/>
    <input type="submit" value="Login" />
</form>
</body>
</html>
