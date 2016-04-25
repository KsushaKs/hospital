<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksu
  Date: 08.04.16
  Time: 17:16
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
    <title>Binding</title>

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
<div class="table-responsive">
    <table class="table table-bordered">
        <tr>
            <th>doctor's id</th>
            <th>first name</th>
            <th>last name</th>
            <th>available</th>
            <th>speciality's id</th>

        </tr>

        <c:forEach var="bind" items="${binding}">
            <tr>
                <td>
                        ${doctor.id}
                </td>
                <td>
                        ${doctor.firstName}
                </td>
                <td>
                        ${doctor.lastName}
                </td>
                <td>
                    <c:if test="${doctor.available}">
                        <span class="glyphicon glyphicon-ok" style="color : green"></span>
                    </c:if>
                    <c:if test="${!doctor.available}">
                        <span class="glyphicon glyphicon-remove" style="color:red"></span>
                    </c:if>
                </td>
                <td>
                        ${bind}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<form action="/ss">
    <input name="title" type="text" value="${speciality.title}">
    <input name="action" type="submit" value="add speciality">
</form>
</body>
</html>
