<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="own" uri="http://mycompany.com" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content" var="var"/>
<html>
<body>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <form>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=To_Main_Page"><fmt:message key="link.main"
                                                                                            bundle="${var}"/></a>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=print_tariffs&pageNumber=0"><fmt:message
                        key="link.tariffs" bundle="${var}"/></a>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=print_discounts&pageNumber=0"><fmt:message
                        key="link.discounts" bundle="${var}"/></a>
                <own:userLink/>
            </form>
        </div>
        <c:choose>
            <c:when test="${empty login}">
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="sign_in"/>
                        <div class="form-group">
                            <input type="text" placeholder=
                                <fmt:message key="input.login" bundle="${var}"/>
                                    class="form-control" name="login">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder=
                                <fmt:message key="input.password" bundle="${var}"/>
                                    class="form-control" name="password">
                        </div>
                        <button type="submit" class="btn btn-success"><fmt:message key="button.signIn" bundle="${var}"/></button>
                        </br>
                        <span class="ui-state-error" style="color: red;">${error}</span>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="sign_out"/>
                        <c:choose>
                            <c:when test="${role == 'user'}">
                                <a class="navbar-link" style="font-size: 16px;"
                                   href="${pageContext.request.contextPath}/controller?command=To_Profile">${login}</a>
                            </c:when>
                            <c:otherwise>
                                <span class="navbar-link" style="font-size: 16px;">${login}</span>
                            </c:otherwise>
                        </c:choose>
                        <button type="submit" class="btn btn-success"><fmt:message key="button.signOut" bundle="${var}"/></button>
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
