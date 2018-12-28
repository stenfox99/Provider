<%@ taglib prefix="info" uri="http://mycompany.com" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<body>
<div align="center">
    <footer>
        <p>© 2018 Training</p>
        <form>
            <a href="controller?command=language&language=en_EN">EN</a>
            <a href="controller?command=language&language=ru_RU">RU</a></br>
        </form>
        <info:printInfo/>
    </footer>
</div>
</body>
</html>
