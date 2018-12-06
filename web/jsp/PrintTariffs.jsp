<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Tariffs</title>
</head>
<body>
<jsp:include page="/jsp/common/Header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="row">
            <c:forEach var="element" items="${printedTariffs}">
                <div class="col-md-4">
                    <h2>${element.name}</h2>
                    <h3>${element.price}$</h3>
                    <p>${element.description}</p>
                    <p><a class="btn btn-default" href="#" role="button">Select tariff</a></p>
                </div>
            </c:forEach>
        </div>
        <nav align="center">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${countPage+1}">
                    <li class="page-item"><a class="page-link"
                                             href="controller?command=print_tariffs&pageNumber=${i-1}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
        <hr>
    </div>
</div>
<jsp:include page="/jsp/common/Footer.jsp"/>
</body>
</html>
