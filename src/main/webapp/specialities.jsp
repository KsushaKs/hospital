<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksu
  Date: 08.04.16
  Time: 12:58
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
    <title>Specialities</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/moment-with-locales.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>
<form action="/ss">
    <input name="action" type="submit" value="Specialities"/>
</form>
<div class="container">
    <div class="row">

        <div class="col-md-12">
            <h4>Specialties</h4>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <tr>
                        <th>id</th>
                        <th>Title</th>
                        <th>action</th>
                    </tr>
                    <c:forEach var="speciality" items="${specialities}">
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
            </div>
        </div>
    </div>
    <form action="/ss">
        <input name="title" type="text" value="${speciality.title}">
        <input name="action" type="submit" value="add">
    </form>
    <form action="/ss">
        <input name="action" type="submit" value="delete empty">
    </form>


</body>
</html>
