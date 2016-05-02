<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        <title>Produkty</title>
    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Produkty</h1>
                    <p>Dodaj produkty</p>
                </div>
            </div>
        </section>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Zaloguj</h3>
                        </div>

                        <div class="panel-body">
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">
                                   Błąd<br />

                                </div>
                            </c:if>
                            <form action="<c:url value="/j_spring_security_check"></c:url>" method="post">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="from-control" placeholder="Nazwa uzytkownika" name='j_username' type="text">
                                    </div>
                                    <div class="form-group">
                                        <input class="from-control" placeholder="Haslo" name='j_password' type="password" value="" >
                                    </div>
                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Zaloguj sie">
                                </fieldset>

                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
