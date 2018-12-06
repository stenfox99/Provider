<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/MainStyle.css"/>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <form>
                <a class="navbar-brand" href="controller?command=To_Main_Page">Main</a>
                <a class="navbar-brand" href="controller?command=print_tariffs&pageNumber=0">Tariffs</a>
                <a class="navbar-brand" href="#">Discounts</a>
            </form>
        </div>
        <c:choose>
            <c:when test="${empty login}">
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" action="controller" method="post">
                        <input type="hidden" name="command" value="sign_in"/>
                        <div class="form-group">
                            <input type="text" placeholder="Login" class="form-control" name="login">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" class="form-control" name="password">
                        </div>
                        <button type="submit" class="btn btn-success">Sign in</button>
                        </br>
                        <span class="ui-state-error" style="color: red;">${error}</span>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" action="controller" method="post">
                        <input type="hidden" name="command" value="sign_out"/>
                        <a class="nav-link" href="controller?command=To_Profile">${login}</a>
                        <button type="submit" class="btn btn-success">Sign out</button>
                        </br>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>
</div>
</body>
</html>
