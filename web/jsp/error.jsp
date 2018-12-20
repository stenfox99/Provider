<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"/>
<div>
    <div class="jumbotron">
        <div class="container">
            <h1>Status code: ${pageContext.errorData.statusCode}</h1>
            <p>Request from ${pageContext.errorData.requestURI} is failed</p>
            <p>Servlet name: ${pageContext.errorData.servletName}</p>
            <p>Exception: ${pageContext.exception}</p>
            <p>Message from exception: ${pageContext.exception.message}</p>
        </div>
    </div>
</div>
<jsp:include page="/jsp/common/footer.jsp"/>
</body>
</html>
