<%--
  Created by IntelliJ IDEA.
  User: Kamil
  Date: 30/03/2016
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="pl_PL">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rejestracja</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <style>
        body {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 14px;
            line-height: 1.42857143;
            background-color: darkslategray;
            color: #ffa62a;
        }
    </style>

</head>
<body>
<div id="page">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                        <h1 class="text-center"><strong><spring:message code="page.title.label"/> </strong><br/>
                            <small><spring:message code="registerPage.register.label"/>
                            </small>
                        </h1>
                </div>
                <div class="row">
                    <div class="col-md-12"><br/><br/></div>
                </div>
                <div class="row">
                    <div class="col-md-12 ">
                        <div class="row">
                            <div class="col-md-6 col-md-offset-4">
                                <p class="lead"><b><spring:message code="registerPage.register.label"/></b></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 col-md-offset-4" >
                                <form:form modelAttribute="newUser" >
                                    <form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>
                                    <div class="form-group">
                                        <spring:message code="registerPage.login.label" var="login"/>
                                        <label for="inputLogin">${login}</label>
                                        <form:input type="text" class="form-control" id="inputLogin" path="login" placeholder="${login}"/>
                                        <form:errors path="login" cssClass="alert text-danger" />
                                    </div>
                                    <div class="form-group">
                                        <spring:message code="registerPage.email.label" var="email"/>
                                        <label for="inputEmail">${email}</label>
                                        <form:input type="text" class="form-control" id="inputEmail" path="eMail" placeholder="${email}"/>
                                        <form:errors path="eMail" cssClass="alert text-danger" />
                                    </div>
                                    <div class="form-group">
                                        <spring:message code="registerPage.password.label" var="password"/>
                                        <label for="inputPassword">${password}</label>
                                        <form:input type="password" class="form-control" id="inputPassword" path="password" placeholder="${password}"/>
                                        <form:errors  cssClass="alert text-danger" />
                                    </div>
                                    <div class="form-group">
                                        <spring:message code="registerPage.password.repeat.label" var="password2"/>
                                        <label for="inputPassword2">${password2}</label>
                                        <form:input type="password" class="form-control" id="inputPassword2" path="password2" placeholder="${password2}"/>
                                        <form:errors cssClass="alert text-danger" />
                                    </div>
                                    <button type="submit" class="btn btn-default"><spring:message code="registerPage.signUp.label"/>!</button>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-6">
                <!--<img src="https://pixabay.com/static/uploads/photo/2014/12/22/19/59/macbook-577758_960_720.jpg" >-->
            </div>
        </div>

    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>

</body>
</html>
