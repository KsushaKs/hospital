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
<table border="1" cellpadding="1" cellspacing="1">
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
                ${id_doctor}
            </td>
            <td>
                ${bind.firstName}
            </td>
            <td>
                ${bind.lastName}
            </td>
            <td>
                ${bind.available}
            </td>
            <td>
                ${bind.specialties[0]}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
