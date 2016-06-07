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
        <form:form  action="/j_spring_security_check" class="form-horizontal col-md-6 col-md-offset-3">
            <form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>
            <fieldset>
                <!-- Form Name -->
                <legend class="col-md-offset-3 col-md-6">Zaloguj</legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinput">Login</label>
                    <div class="col-md-5">
                        <input id="textinput" name="j_username" type="text" placeholder="Login"
                               class="form-control input-md">

                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="passwordinput">Hasło</label>
                    <div class="col-md-5 ">
                        <input id="passwordinput" name="j_password" type="password" placeholder="Hasło"
                               class="form-control input-md">

                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <div class="col-md-12 col-md-offset-4">
                        <button type="submit" id="singlebutton" name="singlebutton" class="btn btn-primary">Zaloguj</button>
                        <a href="<spring:url value="/register"/>" id="register" name="singlebutton" class="btn btn-warning">Zarejestruj</a>
                    </div>
                </div>

            </fieldset>
        </form:form>


    </div>

</div>
<footer class="navbar-fixed-bottom">
    <p class="container">&copy; Company 2016</p>
</footer>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

<script src="js/vendor/bootstrap.min.js"></script>

<script src="js/main.js"></script>
</body>
</html>
