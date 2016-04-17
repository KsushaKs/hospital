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

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/validNewDoctor.css">


    <script src="js/jquery-1.12.3.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type='text/javascript' src='js/validNewDoctor.js'></script>

</head>
<body>
<form id='testForm1' method="post" action='/addDoctor' onsubmit='return cFM_checktrueAttr($("#testForm1"));'>
    First Name : <input type='text' name="firstName" cfm_check='Y_name' title='first name' />
    <br/>
    Last Name : <input type='text' name="lastName" cfm_check='Y_name' title='last name' /> <br/>
    DOB : <input type="text" name="dob" cfm_check='Y_date' title='date of birth'/>
    <br/>
    Experience : <input type='text' name="experience" cfm_check='Y_num' title='experience' /><br/>
    Available : <input type="text" name="available" cfm_check='Y' title='available'/><br/>
    Speciality :<input type='text' name="speciality" cfm_check='Y' title='speciality'/>
    <br/>
    <input type="submit" value="Submit"/>
</form>

<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1>Form to add a new doctor</h1>
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
                            <form id='testForm' role="form"  class="registration-form"  method="post" action='/addDoctor' onsubmit='return cFM_checktrueAttr($("#testForm"));'>

                                <div class="form-group">
                                    <label class="sr-only" >Report</label>
                                    <input type="text" cfm_check='Y_name' title='first name' maxlength="15"
                                           class="form-control" placeholder="First Name" name="firstName" size="70"/>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label sr-only">Report</label>
                                    <input type="text" cfm_check='Y_name' title='last name' class="form-control"
                                           placeholder="Last Name" name="lastName" size="70"/>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label sr-only">Date</label>
                                    <input type='text' cfm_check='Y_date' title='date of birth' class="form-control"
                                           name="dob" placeholder="Date..."/>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="">Report</label>
                                    <input type="text" cfm_check='Y_num' title='experience' class="form-control"placeholder="Experience" name="experience" size="70"/>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="">Report</label>
                                    <input type="text" cfm_check='Y' title='available' class="form-control"placeholder="Available" name="available" size="70"/>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="">Report</label>
                                    <input type="text" cfm_check='Y' title='speciality'
                                           class="form-control"placeholder="Speciality" name="speciality" size="70"/>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="">Report</label>
                                    <textarea name="report" placeholder="Report..." class="form-report form-control"
                                              id=""></textarea>
                                </div>
                                <button type="submit" class="btn" name="action" value="Submit">Add Doctor</button>

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
