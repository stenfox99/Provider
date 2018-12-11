<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Discounts</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<jsp:include page="/jsp/common/Header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:choose>
            <c:when test="${role == 'admin'}">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                        data-whatever="@mdo">Add new discount
                </button>
                <span class="ui-state-error" style="color: red;">${error}</span>
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Info about tariff</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="controller" method="post">
                                    <input type="hidden" value="add_discount" name="command">
                                    <div class="form-group">
                                        <label for="discount-name" class="col-form-label">Discount name:</label>
                                        <input type="text" class="form-control" id="discount-name" name="discountName"
                                               required
                                               pattern="[\w\d]{6,20}">
                                    </div>
                                    <div class="form-group">
                                        <label for="tariffName">Tariff name</label>
                                        <input list="tariff-name-list" class="form-control" id="tariffName" name="tariffName">
                                        <datalist id="tariff-name-list">
                                            <c:forEach var="tariff" items="${tariffs}">
                                                <option>${tariff.name}</option>
                                            </c:forEach>
                                        </datalist>
                                    </div>
                                    <div class="form-group">
                                        <label for="discount" class="col-form-label">Discount</label>
                                        <input type="text" class="form-control" id="discount" name="discount" required
                                               pattern="[\d]{1,4}">
                                    </div>
                                    <div class="form-group">
                                        <label for="message-text" class="col-form-label">Description</label>
                                        <textarea class="form-control" id="message-text" name="description"
                                                  required></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="beginning-date" class="col-form-label">Beginning date</label>
                                        <input type="date" class="form-control" id="beginning-date" name="beginningDate"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label for="end-date" class="col-form-label">End date</label>
                                        <input type="date" class="form-control" id="end-date" name="endDate"
                                               required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Add discount</button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Discount name</th>
                <th scope="col">Tariff name</th>
                <th scope="col">Discount(%)</th>
                <th scope="col">Description</th>
                <th scope="col">Beginning date</th>
                <th scope="col">End date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${printedDiscounts}">
                <form action="controller" method="post">
                    <tr>
                        <c:choose>
                            <c:when test="${role == 'admin'}">
                                <input type="hidden" value="update_discount" name="command">
                                <td>
                                    <input type="hidden" name="discountName" value="${element.name}">
                                        ${element.name}
                                </td>
                                <td>
                                    <input list="tariff-name-list-edit" name="tariffName" value="${element.tariff.name}">
                                    <datalist id="tariff-name-list-edit">
                                        <c:forEach var="tariff" items="${tariffs}">
                                            <option>${tariff.name}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                                <td>
                                    <input type="text" name="discount" value="${element.discount}">
                                </td>
                                <td>
                                    <textarea type="textarea" name="description">${element.description}</textarea>
                                </td>
                                <td>
                                    <input type="date" name="beginningDate" value="${element.beginningDate}">
                                </td>
                                <td>
                                    <input type="date" name="endDate" value="${element.endDate}">
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
                                    <button type="submit">Save changes</button>
                                </td>
                                <td>
                                    <a href="controller?command=remove_discount&discountName=${element.name}">Delete
                                        discount</a>
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
                                             href="controller?command=print_discounts&pageNumber=${i-1}">${i}</a>
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
