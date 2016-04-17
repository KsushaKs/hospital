<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksu
  Date: 08.04.16
  Time: 13:22
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
    <title>Doctors</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/validNewDoctor.css">

    <script src="js/jquery-1.12.3.min.js"></script>
    <script type='text/javascript' src='js/validNewDoctor.js'></script>
    <script type='text/javascript' src='js/deleteConfim.js'></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>

<form action="/ms">
    <input name="action" type="submit" value="Specialities"/>
    <input name="action" type="submit" value="Doctors"/>
</form>
<div id="myModal" class="modal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Delete</h4>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete </p>
            </div>
            <div class="modal-footer">
                <a id="confirm" href="#" class="btn btn-danger">Yes</a>
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">

        <div class="col-md-12">
            <h4>Doctors</h4>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <tr>
                        <th>id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Date Of Birth</th>
                        <th>Experience</th>
                        <th>Available</th>
                        <th>action</th>
                    </tr>
                    <c:forEach var="doctor" items="${doctors}">
                        <tr>
                            <form action="">
                                <td>

                                    <p><input name="action" type="submit" value="${doctor.id}"/></p>
                                    <input name="id" type="hidden" value="${doctor.id}"/>
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

                                    <c:if test="${doctor.available}">
                                        <span class="glyphicon glyphicon-ok" style="color : green"></span>
                                    </c:if>
                                    <c:if test="${!doctor.available}">
                                        <span class="glyphicon glyphicon-remove" style="color:red"></span>
                                    </c:if>
                                </td>
                                <td>

                                    <!--  <a href="/ms?action=delete&id=${doctor.id}"></a>-->
                                    <button id="${doctor.id}" type="button" data-toggle="modal" data-target="#myModal"
                                            class="glyphicon glyphicon-trash">
                                    </button>

                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

    <form action="/addDoctor">
        <button type="submit" class="btn btn-primary">Add Doctor</button>
    </form>
</div>
</body>
</html>
