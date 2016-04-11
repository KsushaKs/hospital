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
<form action="/ss">
    <input name="action" type="submit" value="Specialities"/>
</form>
<table border="1" cellpadding="1" cellspacing="1">
    <tr>
        <th>id</th>
        <th>Title</th>
        <th>action</th>
    </tr>
    <c:forEach  var="speciality" items="${specialities}">
        <tr>
            <td>${speciality.id}</td>
            <td>
                <a href="/ss?action=spec&title=${speciality.title}">${speciality.title}</a>
            </td>
            <td>
                <input name="action" type="submit" value="edit">
            </td>
        </tr>
    </c:forEach>

</table>
<form action="/ss"  >
    <input name="title" type="text" value="${speciality.title}">
    <input name="action" type="submit" value="add">
</form>
<form action="/ss" >
    <input name="action" type="submit" value="delete">
</form>


</body>
</html>
