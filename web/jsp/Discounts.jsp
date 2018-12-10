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
                        data-whatever="@mdo">Add new tariff
                </button>
                <span class="ui-state-error" style="color: red;">${error}</span>
            </c:when>
        </c:choose>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
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
                            <input type="hidden" value="add_tariff" name="command">
                            <div class="form-group">
                                <label for="tariff-name" class="col-form-label">Tariff name:</label>
                                <input type="text" class="form-control" id="tariff-name" name="tariffName" required
                                       pattern="[\w\d]{6,20}">
                            </div>
                            <div class="form-group">
                                <label for="price" class="col-form-label">Price</label>
                                <input type="text" class="form-control" id="price" name="tariffPrice" required
                                       pattern="[\d]{1,4}(\.[\d]{1,2}})?">
                            </div>
                            <div class="form-group">
                                <label for="month-traffic" class="col-form-label">Month traffic</label>
                                <input type="text" class="form-control" id="month-traffic" name="monthTraffic" required
                                       pattern="[\d]{1,6}">
                            </div>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Description</label>
                                <textarea class="form-control" id="message-text" name="description" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Add tariff</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Discount name</th>
                <th scope="col">tariff id</th>
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
                                <input type="hidden" value="update_tariff" name="command">
                                <td>
                                    <input type="hidden" name="tariffName" value="${element.name}">
                                        ${element.name}
                                </td>
                                <td>
                                    <input type="text" name="tariffPrice" value="${element.price}">
                                </td>
                                <td>
                                    <input type="text" name="monthTraffic" value="${element.monthTraffic}">
                                </td>
                                <td>
                                    <textarea type="textarea" name="description">${element.description}</textarea>
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
                                    <a href="controller?command=remove_tariff&tariffName=${element.name}">Delete
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
