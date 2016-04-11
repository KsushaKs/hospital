<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksu
  Date: 12.04.16
  Time: 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Docs</title>
</head>
<body>
<table border="1" cellpadding="1" cellspacing="1">
    <tr>
        <th>id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date Of Birth</th>
        <th>Experience</th>
        <th>Available</th>
        <th>Speciality</th>
    </tr>
    <c:forEach var="doctor" items="${doctors}">
        <tr><form action="">
            <td>

                ${doctor.id}
            </td>
            <td>
                <c:out value="${doctor.firstName}"/>
            </td>
            <td>
                <c:out value="${doctor.lastName}"/>
            </td>
            <td>
                    ${doctor.birthDate}
            </td>
            <td>
                <c:out value="${doctor.experience}"/>
            </td>
            <td>
                    ${doctor.available}
            </td>
            <td>
                ${doctor.specialties[0]}
            </td>
        </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
