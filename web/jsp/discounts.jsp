<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content" var="var"/>
<html>
<head>
    <title>Discounts</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:choose>
            <c:when test="${role == 'admin'}">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                        data-whatever="@mdo"><fmt:message key="button.addDiscount" bundle="${var}"/>
                </button>
                <span class="ui-state-error" style="color: red;">${error}</span>
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="label.infoAboutDiscount" bundle="${var}"/></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" value="add_discount" name="command">
                                    <div class="form-group">
                                        <label for="discount-name" class="col-form-label"><fmt:message key="label.discountName" bundle="${var}"/>*</label>
                                        <input type="text" class="form-control" id="discount-name" name="discountName"
                                               required pattern="[\w\d\s]{6,20}">
                                    </div>
                                    <div class="form-group">
                                        <label for="tariffName"><fmt:message key="label.tariffName" bundle="${var}"/>*</label>
                                        <input list="tariff-name-list" class="form-control" id="tariffName" name="tariffName" required>
                                        <datalist id="tariff-name-list">
                                            <c:forEach var="tariff" items="${tariffs}">
                                                <option>${tariff.name}</option>
                                            </c:forEach>
                                        </datalist>
                                    </div>
                                    <div class="form-group">
                                        <label for="discount" class="col-form-label"><fmt:message key="label.discount" bundle="${var}"/>*</label>
                                        <input type="text" class="form-control" id="discount" name="discount" required
                                               pattern="[\d]{1,4}">
                                    </div>
                                    <div class="form-group">
                                        <label for="message-text" class="col-form-label"><fmt:message key="label.description" bundle="${var}"/>*</label>
                                        <textarea class="form-control" id="message-text" name="description"
                                                  required></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="beginning-date" class="col-form-label"><fmt:message key="label.beginningDate" bundle="${var}"/>*</label>
                                        <input type="date" class="form-control" id="beginning-date" name="beginningDate"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label for="end-date" class="col-form-label"><fmt:message key="label.endDate" bundle="${var}"/>*</label>
                                        <input type="date" class="form-control" id="end-date" name="endDate"
                                               required>
                                    </div>
                                    <button type="submit" class="btn btn-primary"><fmt:message key="button.addDiscount" bundle="${var}"/></button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="button.close" bundle="${var}"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="label.discountName" bundle="${var}"/></th>
                <th scope="col"><fmt:message key="label.tariffName" bundle="${var}"/></th>
                <th scope="col"><fmt:message key="label.discount" bundle="${var}"/>(%)</th>
                <th scope="col"><fmt:message key="label.description" bundle="${var}"/></th>
                <th scope="col"><fmt:message key="label.beginningDate" bundle="${var}"/></th>
                <th scope="col"><fmt:message key="label.endDate" bundle="${var}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${printedDiscounts}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <tr>
                        <c:choose>
                            <c:when test="${role == 'admin'}">
                                <input type="hidden" value="update_discount" name="command">
                                <td>
                                    <input type="hidden" name="discountName" value="${element.name}">
                                        ${element.name}
                                </td>
                                <td>
                                    <input list="tariff-name-list-edit" name="tariffName" value="${element.tariff.name}" required>
                                    <datalist id="tariff-name-list-edit">
                                        <c:forEach var="tariff" items="${tariffs}">
                                            <option>${tariff.name}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                                <td>
                                    <input type="text" name="discount" value="${element.discount}" required>
                                </td>
                                <td>
                                    <textarea type="textarea" name="description" required>${element.description}</textarea>
                                </td>
                                <td>
                                    <input type="date" name="beginningDate" value="${element.beginningDate}" required>
                                </td>
                                <td>
                                    <input type="date" name="endDate" value="${element.endDate}" required>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>${element.name}</td>
                                <td>${element.tariff.name}</td>
                                <td>${element.discount}</td>
                                <td>${element.description}</td>
                                <td>${element.beginningDate}</td>
                                <td>${element.endDate}</td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${role == 'admin'}">
                                <td>
                                    <button type="submit"><fmt:message key="button.saveChange" bundle="${var}"/></button>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/controller?command=remove_discount&discountName=${element.name}">
                                        <fmt:message key="button.deleteDiscount" bundle="${var}"/></a>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>
        <nav align="center">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${countPage}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/controller?command=print_discounts&pageNumber=${i-1}">${i}</a>
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
