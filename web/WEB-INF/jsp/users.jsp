<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content" var="var"/>
<html>
<head>
    <title>Users</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                data-whatever="@mdo"><fmt:message key="button.addUser" bundle="${var}"/>
        </button><br/>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" value="fill_traffic" name="command">
            <button type="submit" class="btn btn-primary"><fmt:message key="button.distributeTraffic" bundle="${var}"/></button>
        </form>
        <span class="ui-state-error" style="color: red;">${error}</span>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="label.infoAboutUser" bundle="${var}"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" value="add_user" name="command">
                            <div class="form-group">
                                <label for="user-name" class="col-form-label"><fmt:message key="input.login" bundle="${var}"/>*</label>
                                <input type="text" class="form-control" id="user-name" name="login" required
                                       pattern="[\w\d]{4,16}">
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-form-label"><fmt:message key="input.password" bundle="${var}"/>*</label>
                                <input type="password" class="form-control" id="password" name="password" required
                                       pattern="[\w\d]{6,20}">
                            </div>
                            <div class="form-group">
                                <label for="userType"><fmt:message key="label.userType" bundle="${var}"/></label>
                                <input list="user-type-list" class="form-control" id="userType" name="userType">
                                <datalist id="user-type-list">
                                    <c:forEach var="userType" items="${userType}">
                                        <option>${userType.userTypeValue}</option>
                                    </c:forEach>
                                </datalist>
                            </div>
                            <button type="submit" class="btn btn-primary"><fmt:message key="button.addUser" bundle="${var}"/></button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="button.close" bundle="${var}"/></button>
                    </div>
                </div>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="input.login" bundle="${var}"/></th>
                <th scope="col"><fmt:message key="label.userType" bundle="${var}"/></th>
                <th scope="col"><fmt:message key="label.ban" bundle="${var}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${printedUsers}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <tr>
                        <td>${element.login}</td>
                        <td>${element.userType.userTypeValue}</td>
                        <td>${element.ban}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=ban_user&login=${element.login}"><fmt:message key="button.ban" bundle="${var}"/></a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=unban_user&login=${element.login}"><fmt:message key="button.unban" bundle="${var}"/></a>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>
        <nav align="center">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${countPage}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/controller?command=print_user&pageNumber=${i-1}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
        <hr>
    </div>
</div>
<jsp:include page="/jsp/common/footer.jsp"/>
</body>
</html>
