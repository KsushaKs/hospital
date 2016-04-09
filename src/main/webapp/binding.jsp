<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksu
  Date: 08.04.16
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Binding</title>
</head>
<body>
<table border="1" cellpadding="1" cellspacing="1">
    <tr>
        <th>speciality's id</th>
        <th>doctor's id</th>
    </tr>

    <c:forEach var="bind" items="${binding}">
        <tr>
            <td>
                ${id_doctor}
            </td>
            <td>
                ${bind}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
