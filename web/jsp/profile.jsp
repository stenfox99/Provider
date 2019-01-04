<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content" var="var"/>
<html>
<head>
    <title>Profile</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-sm-10"><h1>${login}</h1></div>
        </div>
        <div class="row">
            <div class="col-sm-3">
                <form method="post" action="controller" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="upload_image">
                    <div class="text-center">
                        <c:choose>
                            <c:when test="${empty userData.photo}">
                                <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
                                     class="avatar img-circle img-thumbnail"
                                     alt="avatar">
                            </c:when>
                            <c:otherwise>
                                <img src="data:image/jpeg;base64,${userData.photo}"
                                     class="avatar img-circle img-thumbnail"
                                     alt="avatar">
                            </c:otherwise>
                        </c:choose>
                        <h4>1024 x 1024</h4>
                        <input type="file" class="text-center center-block file-upload" required name="image"><br/>
                        <button type="submit"><fmt:message key="button.confirm" bundle="${var}"/></button>
                        <span class="ui-state-error" style="color: red;">${imageError}</span>
                    </div>
                </form>
                </hr><br>
                <h2><fmt:message key="label.tariffName" bundle="${var}"/>:${userData.tariff.name}</h2>
            </div>
            <div class="col-sm-9">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#changeInfo"><fmt:message key="menu.changeProfileInfo"
                                                                                            bundle="${var}"/></a></li>
                    <li><a data-toggle="tab" href="#changePas"><fmt:message key="menu.changePassword"
                                                                            bundle="${var}"/></a></li>
                    <li><a data-toggle="tab" href="#balanceTab"><fmt:message key="menu.balanceTraffic"
                                                                             bundle="${var}"/></a></li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane active" id="changeInfo">
                        <hr>
                        <form class="form" action="controller" method="post" id="changeForm">
                            <input type="hidden" value="change_profile_info" name="command">
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="first_name"><h4><fmt:message key="label.firstName"
                                                                             bundle="${var}"/></h4></label>
                                    <input type="text" class="form-control" name="firstName" id="first_name"
                                           title="enter your first name if any."
                                           value="${userData.firstName}" pattern="[a-zA-Z]{2,12}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="last_name"><h4><fmt:message key="label.surname" bundle="${var}"/></h4>
                                    </label>
                                    <input type="text" class="form-control" name="lastName" id="last_name"
                                           title="enter your last name if any."
                                           value="${userData.lastName}" pattern="[a-zA-Z]{2,12}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="last_name"><h4><fmt:message key="label.patronymic"
                                                                            bundle="${var}"/></h4></label>
                                    <input type="text" class="form-control" name="patronymic" id="patronymic"
                                           title="enter your patronymic if any."
                                           value="${userData.patronymic}" pattern="[a-zA-Z]{2,12}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="phone"><h4><fmt:message key="label.phone" bundle="${var}"/></h4></label>
                                    <input type="text" class="form-control" name="phone" id="phone"
                                           title="enter your phone number if any."
                                           value="${userData.phone}" pattern="\+\d{12}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="email"><h4><fmt:message key="label.email" bundle="${var}"/></h4></label>
                                    <input type="email" class="form-control" name="email" id="email"
                                           title="enter your email."
                                           value="${userData.email}" pattern="[\w\d]+@mail\.ru|[\w\d]+@gmail\.com">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <br>
                                    <button class="btn btn-lg btn-success" type="submit"><i
                                            class="glyphicon glyphicon-ok-sign"></i> <fmt:message
                                            key="button.saveChange" bundle="${var}"/>
                                    </button>
                                    <span class="ui-state-error" style="color: red;">${changeInfoError}</span>
                                </div>
                            </div>
                        </form>
                        <hr>
                    </div>

                    <div class="tab-pane" id="changePas">
                        <hr>
                        <form class="form" action="controller" method="post" id="changePassword">
                            <input type="hidden" value="change_password" name="command">
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="password"><h4><fmt:message key="label.password" bundle="${var}"/></h4>
                                    </label>
                                    <input type="password" class="form-control" name="password" id="password"
                                           title="enter your password." pattern="[\w\d]{6,20}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="password2"><h4><fmt:message key="label.verify" bundle="${var}"/></h4>
                                    </label>
                                    <input type="password" class="form-control" name="password2" id="password2"
                                           title="enter your password2." pattern="[\w\d]{6,20}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <br>
                                    <button class="btn btn-lg btn-success" type="submit"><i
                                            class="glyphicon glyphicon-ok-sign"></i> <fmt:message
                                            key="button.saveChange" bundle="${var}"/>
                                    </button>
                                    <span class="ui-state-error" style="color: red;">${changePasswordError}</span>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="balanceTab">
                        <div class="tab-pane" id="settings">
                            <hr>
                            <form class="form" action="controller" method="post" id="balanceForm">
                                <input type="hidden" value="increase_balance" name="command">
                                <input type="hidden" value="${userData.balance}" name="currentBalance">
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label for="balance"><h4><fmt:message key="label.balance"
                                                                              bundle="${var}"/>:${userData.balance}</h4>
                                        </label>
                                        <input type="text" class="form-control" name="balance" id="balance"
                                               pattern="\d{1,4}"
                                               title="enter balance to add.">
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <br>
                                            <button class="btn btn-lg btn-success" type="submit"><i
                                                    class="glyphicon glyphicon-ok-sign"></i> <fmt:message
                                                    key="button.increaseBalance" bundle="${var}"/>
                                            </button>
                                            <span class="ui-state-error"
                                                  style="color: red;">${increaseBalanceError}</span>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <h4><fmt:message key="label.traffic" bundle="${var}"/>:${userData.traffic}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="/jsp/common/footer.jsp"/>
</body>
</html>
