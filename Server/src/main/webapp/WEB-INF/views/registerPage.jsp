<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" %>
<!doctype html>
<html class="no-js" lang="" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="<c:url value="../../resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="../../resources/css/bootstrap-theme.min.css"/>" rel="stylesheet"  type="text/css">
    <link href="<c:url value="../../resources/css/main.css"/>" rel="stylesheet"  type="text/css">
    <!-- Custom styles for this template -->

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-fixed-top">
    <div class="container menu">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">ShareALL</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<spring:url value="/room/add"/>">Dodaj pokój</a></li>
                <li><a href="<spring:url value="/image"/>">Przejdź do transmisji</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<spring:url value="/login"/>">Logowanie</a></li>
                <li><a href="<spring:url value="/register"/>">Rejestracja</a></li>
            </ul>
        </div>
    </div>

</nav>

<!-- Main jumbotron for a primary marketing message or call to action -->

<div class="container">
    <div class="posision-login">
        <form:form  modelAttribute="newUser" class="form-horizontal col-md-6 col-md-offset-3">
            <form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>
            <fieldset>
                <!-- Form Name -->
                <legend class="col-md-offset-3 col-md-6"><spring:message code="registerPage.register.label"/></legend>

                <!-- Text input-->
                <div class="form-group">
                    <spring:message code="registerPage.login.label" var="login"/>
                    <label class="col-md-4 control-label" for="textinput">${login}</label>
                    <div class="col-md-5">
                        <form:input id="textinput" path="login"  type="text" placeholder="${login}"
                               class="form-control input-md"/>
                        <form:errors path="login" cssClass="alert text-danger" />
                    </div>
                </div>
                <!-- Text input-->
                <div class="form-group">
                    <spring:message code="registerPage.email.label" var="email"/>
                    <label class="col-md-4 control-label" for="mailinput">${email}</label>
                    <div class="col-md-5">
                        <form:input id="mailinput" path="eMail" type="email" placeholder="${email}"
                               class="form-control input-md"/>
                        <form:errors path="eMail" cssClass="alert text-danger" />

                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <spring:message code="registerPage.password.label" var="password"/>
                    <label class="col-md-4 control-label" for="passwordinput">${password}</label>
                    <div class="col-md-5 ">
                        <form:input id="passwordinput" path="password" type="password" placeholder="Hasło"
                               class="form-control input-md"/>
                        <form:errors  cssClass="alert text-danger" />

                    </div>
                </div>
                <!-- Password input-->
                <div class="form-group">
                    <spring:message code="registerPage.password.repeat.label" var="password2"/>
                    <label class="col-md-4 control-label" for="passwordinput2">${password2}</label>
                    <div class="col-md-5 ">
                        <form:input id="passwordinput2" path="password2" type="password" placeholder="Powtórz hasło"
                               class="form-control input-md"/>
                        <form:errors  cssClass="alert text-danger" />
                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <div class="col-md-12 col-md-offset-4">
                        <button type="submit" id="singlebutton" name="singlebutton" class="btn btn-primary">Zaloguj</button>

                    </div>
                </div>

            </fieldset>
        </form:form>


    </div>

</div>
<footer class="navbar-fixed-bottom">
    <p class="container">&copy; Company 2016</p>
</footer>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

<script src="js/vendor/bootstrap.min.js"></script>

<script src="js/main.js"></script>
</body>
</html>
