<%@ taglib prefix="check" uri="http://mycompany.com" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content" var="var"/>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <title>Provider</title>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"/>
<div>
    <div class="jumbotron">
        <div class="container">
            <h1>EPAM Provider</h1>
            <h2><fmt:message key="label.aboutUs" bundle="${var}"/></h2>
            <p><fmt:message key="label.aboutUsText" bundle="${var}"/></p>
        </div>
    </div>
    <div class="container">
        <h1><fmt:message key="label.specialOffers" bundle="${var}"/></h1>
        <div class="row">
            <c:forEach items="${printedDiscounts}" var="element">
                <div class="col-md-4">
                    <h2>${element.name}</h2>
                    <h4><fmt:message key="label.discountValue" bundle="${var}"/> : ${element.discountValue}%</h4>
                    <h4><fmt:message key="label.tariffByDiscount" bundle="${var}"/> : ${element.tariff.name}</h4>
                    <h4><fmt:message key="label.beginningDate" bundle="${var}"/> : ${element.beginningDate}</h4>
                    <h4><fmt:message key="label.endDate" bundle="${var}"/> : ${element.endDate}</h4>
                    <p>${element.description}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<br/>
<br/>
<jsp:include page="/jsp/common/footer.jsp"/>
</body>
</html>
