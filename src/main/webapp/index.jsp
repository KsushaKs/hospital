<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksu
  Date: 07.04.16
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Hospital</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/validNewDoctor.css">


    <script type='text/javascript' src='js/validNewDoctor.js'></script>
    <script src="js/jquery-1.12.3.min.js"></script>
    <script src="js/bootstrap.min.js"></script>


</head>
<body>
<form action="/ms">
    <input name="action" type="submit" value="Specialities"/>
    <input name="action" type="submit" value="Doctors"/>
</form>
<table border="1" cellpadding="1" cellspacing="1" style="width:500px">
    <tbody>
    <tr>
        <td>
            <form action="/ms">
                <p><input name="action" type="submit" value="Doctors"/></p>
                <p><input name="action" type="submit" value="Specialities"/></p>
            </form>
        </td>
        <td>


            <c:if test="${added == true}">
            </c:if>

            <c:if test="${speciality == true}">
            <c:out value="${speciality}"/><p>
            <c:if test="${dataList == true}">
            Doctor:
            <p>
                <c:forEach var="speciality" items="${dataList}">
                <a href="http://localhost:8080/ms?action=Specialities"><c:out value="${speciality}"/> </a>
            <p>
                </c:forEach>
                </c:if>
                <c:if test="${dataList == true}">
                Speciality:
            <p>
                <a href="http://localhost:8080/ms?action=Doctors"><c:out value="${doctor}"/> </a>
            <p>
                </c:if>
                </c:if>


        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
