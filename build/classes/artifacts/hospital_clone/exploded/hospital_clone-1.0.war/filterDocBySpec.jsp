<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksu
  Date: 12.04.16
  Time: 1:01
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

    <title>Docs</title>

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
<div class="container">
    <div class="row">

        <div class="col-md-12">
            <c:set var="spec" value="${doctors[0]}"/>
            <h4>All Doctors with speciality ${title}</h4>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <tr>
                        <th>id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Date Of Birth</th>
                        <th>Experience</th>
                        <th>Available</th>
                    </tr>
                    <c:forEach var="doctor" items="${doctors}">
                        <tr>
                            <form action="">
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

                            </form>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    </div>
</body>
</html>
