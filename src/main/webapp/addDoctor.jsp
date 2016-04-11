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
<html>
<head>
    <title>add Doctor</title>
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
</body>
</html>
