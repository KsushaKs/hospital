<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ksu
  Date: 11.04.16
  Time: 21:59
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

    <title>add Doctor</title>

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
<form  method="post" action="/addDoctor" >
    First Name : <input type="text" name="firstName"/>
    <br/>
    Last Name : <input type="text" name="lastName"  /> <br/>
    DOB : <input
        type="text" name="dob"/>
    <br/>
    Experience : <input type="text" name="experience"/><br/>
    Available : <input type="text" name="available"/><br/>
    Speciality :<input type="text" name="speciality"/>
    <br/>
    <input type="submit" value="Submit"/>
</form>
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1>Form to add a new analyzes</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>Doctor</h3>

                                <p>To add a new doctor, please fill all fields:</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-pencil"></i>
                            </div>
                        </div>

                        <div class="form-bottom">
                            <form role="form" action="/addDoctor" method="POST" class="registration-form">
                                <div class="form-group">
                                    <label class="sr-only" >Report</label>
                                    <input type="text" pattern="[A-Z]{1}\D" maxlength="15" class="form-control" placeholder="First Name" name="firstName" size="70"/>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label sr-only">Report</label>
                                    <input type="text" class="form-control" placeholder="Last Name" name="lastName" size="70"/>
                                    <div class="alert alert-danger" role="alert">
                                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                    <span class="sr-only">Error:</span>
                                    Enter a valid name
                                        </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label sr-only">Date</label>
                                    <input type='text' class="form-control"  name="dob"
                                           placeholder="Date..."/>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="">Report</label>
                                    <input type="text" class="form-control"placeholder="Experience" name="experience" size="70"/>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="">Report</label>
                                    <input type="text" class="form-control"placeholder="Available" name="available" size="70"/>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="">Report</label>
                                    <input type="text" class="form-control"placeholder="Speciality" name="speciality" size="70"/>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="">Report</label>
                                    <textarea name="report" placeholder="Report..." class="form-report form-control"
                                              id=""></textarea>
                                </div>
                                <button type="submit" class="btn">Add Doctor</button>
                                <input type="hidden" name="id" value="">
                            </form>

                            <form  action="/ms" method="get">
                                <button type="submit" name="action" value="Doctors" class="btn btn-primary">Go back to the list with Doctors</button>
                            </form>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
