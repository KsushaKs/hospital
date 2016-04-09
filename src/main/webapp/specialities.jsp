<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksu
  Date: 08.04.16
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Specialities</title>
</head>
<body>
${specialities}
fff
<table border="1" cellpadding="1" cellspacing="1">
    <tr>
        <th>id</th>
        <th>Title</th>
    </tr>
    <c:forEach  var="speciality" items="${specialities}">
        <tr>
            <td>${speciality.id}</td>
            <td>
                ${speciality.title}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
