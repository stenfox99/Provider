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
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Main</a>
            <a class="navbar-brand" href="#">Tariffs</a>
            <a class="navbar-brand" href="#">Discounts</a>
        </div>
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
                <a href="#">EN</a>
                <a href="#">RU</a>
            </form>
        </div>
        <span>${error}</span>
    </div>
</nav>
</div>
</body>
</html>
