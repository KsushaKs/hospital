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


    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/validNewDoctor.css">

    <script src="js/jquery-1.12.3.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/specialityEdit.js"></script>

</head>
<body>
<form action="/ms">
    <input name="action" type="submit" value="Specialities"/>
    <input name="action" type="submit" value="Doctors"/>
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
                            <td >
                                <a id="title${speciality.id}" data-title="${speciality.title}"
                                   href="/ss?action=spec&title=${speciality.title}">${speciality.title}</a>
                            </td>
                            <td>
                                <button id="${speciality.id}" class="update" type="button" value="edit"
                                data-toggle="modal" data-target="#myModal">edit
                                </button>
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
        <input name="action" type="submit" value="show empty">
    </form>

</div>

<div id="myModal" class="modal" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                    <label for="title">title: </label>
                    <input id="title" class="form-control" type="text"/>

            </div>
            <div class="modal-footer">
                <a id="run" href="#" class="btn btn-danger">Update</a>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
