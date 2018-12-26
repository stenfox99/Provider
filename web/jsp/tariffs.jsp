<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content" var="var"/>
<html>
<head>
    <title>Tariffs</title>
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
                        data-whatever="@mdo"><fmt:message key="button.addTariff" bundle="${var}"/>
                </button>
                <span class="ui-state-error" style="color: red;">${error}</span>
            </c:when>
        </c:choose>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="label.infoAboutTariff" bundle="${var}"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="controller" method="post">
                            <input type="hidden" value="add_tariff" name="command">
                            <div class="form-group">
                                <label for="tariff-name" class="col-form-label"><fmt:message key="label.tariffName" bundle="${var}"/>*</label>
                                <input type="text" class="form-control" id="tariff-name" name="tariffName" required
                                       pattern="[\w\d\sа-яА-Я]{3,20}">
                            </div>
                            <div class="form-group">
                                <label for="price" class="col-form-label"><fmt:message key="label.price" bundle="${var}"/>*</label>
                                <input type="text" class="form-control" id="price" name="tariffPrice" required
                                       pattern="\d{1,6}\.\d{2}|\d{1,6}">
                            </div>
                            <div class="form-group">
                                <label for="month-traffic" class="col-form-label"><fmt:message key="label.monthTraffic" bundle="${var}"/>*</label>
                                <input type="text" class="form-control" id="month-traffic" name="monthTraffic" required
                                       pattern="[\d]{1,9}">
                            </div>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label"><fmt:message key="label.description" bundle="${var}"/>*</label>
                                <textarea class="form-control" id="message-text" name="description" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary"><fmt:message key="button.addTariff" bundle="${var}"/></button>
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
                <th scope="col"><fmt:message key="label.tariffName" bundle="${var}"/></th>
                <th scope="col"><fmt:message key="label.price" bundle="${var}"/>($)</th>
                <th scope="col"><fmt:message key="label.priceWithDiscount" bundle="${var}"/></th>
                <th scope="col"><fmt:message key="label.monthTraffic" bundle="${var}"/></th>
                <th scope="col"><fmt:message key="label.description" bundle="${var}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${printedTariffs}">
                <form action="controller" method="post">
                    <tr>
                        <input type="hidden" name="tariffName" value="${element.name}">
                        <c:choose>
                            <c:when test="${role == 'admin'}">
                                <td>${element.name}</td>
                                <td>
                                    <input type="text" name="tariffPrice" value="${element.price}" pattern="\d{1,6}\.\d{2}|\d{1,6}" required>
                                </td>
                                <td>${element.priceWithDiscount}</td>
                                <td>
                                    <input type="text" name="monthTraffic" value="${element.monthTraffic}" pattern="[\d]{1,9}" required>
                                </td>
                                <td>
                                    <textarea type="textarea" name="description" required>${element.description}</textarea>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>${element.name}</td>
                                <td>${element.price}</td>
                                <td>${element.priceWithDiscount}</td>
                                <td>${element.monthTraffic}</td>
                                <td>${element.description}</td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${role == 'admin'}">
                                <input type="hidden" value="update_tariff" name="command">
                                <td>
                                    <button type="submit"><fmt:message key="button.saveChange" bundle="${var}"/></button>
                                </td>
                                <td>
                                    <a href="controller?command=remove_tariff&tariffName=${element.name}">
                                        <fmt:message key="button.deleteTariff" bundle="${var}"/>
                                    </a>
                                </td>
                            </c:when>
                            <c:when test="${role == 'user'}">
                                <input type="hidden" value="connect_to_tariff" name="command">
                                <td>
                                    <button type="submit"><fmt:message key="button.connectToTariff" bundle="${var}"/></button>
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
                                             href="controller?command=print_tariffs&pageNumber=${i-1}">${i}</a>
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
