<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<jsp:include page="/jsp/common/Header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-sm-10"><h1>${login}</h1></div>
        </div>
        <div class="row">
            <div class="col-sm-3">

                <div class="text-center">
                    <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail"
                         alt="avatar">
                    <h6>Upload a different photo...</h6>
                    <input type="file" class="text-center center-block file-upload">
                </div>
                </hr><br>

            </div>
            <div class="col-sm-9">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#changeInfo">Change profile info</a></li>
                    <li><a data-toggle="tab" href="#changePas">Change password</a></li>
                    <li><a data-toggle="tab" href="#balanceTab">Balance/traffic</a></li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane active" id="changeInfo">
                        <hr>
                        <form class="form" action="controller" method="post" id="changeForm">
                            <input type="hidden" value="change_profile_info" name="command">
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="first_name"><h4>First name</h4></label>
                                    <input type="text" class="form-control" name="firstName" id="first_name"
                                           placeholder="first name" title="enter your first name if any."
                                           value="${userData.firstName}" pattern="[\w]{2,12}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="last_name"><h4>Last name</h4></label>
                                    <input type="text" class="form-control" name="lastName" id="last_name"
                                           placeholder="last name" title="enter your last name if any."
                                           value="${userData.lastName}" pattern="[\w]{2,12}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="last_name"><h4>Patronymic</h4></label>
                                    <input type="text" class="form-control" name="patronymic" id="patronymic"
                                           placeholder="patronymic" title="enter your patronymic if any."
                                           value="${userData.patronymic}" pattern="[\w]{2,12}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="phone"><h4>Phone</h4></label>
                                    <input type="text" class="form-control" name="phone" id="phone"
                                           placeholder="enter phone" title="enter your phone number if any."
                                           value="${userData.phone}" pattern="\+\d{12}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="email"><h4>Email</h4></label>
                                    <input type="email" class="form-control" name="email" id="email"
                                           placeholder="you@email.com" title="enter your email."
                                           value="${userData.email}" pattern="[\w\d]+@mail\.ru|[\w\d]+@gmail\.com">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <br>
                                    <span class="ui-state-error" style="color: red;">${changeInfoError}</span>
                                    <button class="btn btn-lg btn-success" type="submit"><i
                                            class="glyphicon glyphicon-ok-sign"></i> Save
                                    </button>
                                </div>
                            </div>
                        </form>
                        <hr>
                    </div>

                    <div class="tab-pane" id="changePas">
                        <hr>
                        <form class="form" action="##" method="post" id="changePassword">
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="password"><h4>Password</h4></label>
                                    <input type="password" class="form-control" name="password" id="password"
                                           placeholder="password" title="enter your password.">
                                </div>
                            </div>
                            <div class="form-group">

                                <div class="col-xs-6">
                                    <label for="password2"><h4>Verify</h4></label>
                                    <input type="password" class="form-control" name="password2" id="password2"
                                           placeholder="password2" title="enter your password2.">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <br>
                                    <button class="btn btn-lg btn-success" type="submit"><i
                                            class="glyphicon glyphicon-ok-sign"></i> Save
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="balanceTab">
                        <div class="tab-pane" id="settings">
                            <hr>
                            <form class="form" action="##" method="post" id="balanceForm">
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label for="balance"><h4>Balance:${userData.balance}</h4></label>
                                        <input type="text" class="form-control" name="balance" id="balance"
                                               placeholder="Balance" title="enter balance to add.">
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <br>
                                            <button class="btn btn-lg btn-success" type="submit"><i
                                                    class="glyphicon glyphicon-ok-sign"></i> Add balance
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form class="form" action="##" method="post" id="trafficForm">
                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label for="password2"><h4>Traffic:${userData.traffic}</h4></label>
                                        <input type="traffic" class="form-control" name="traffic" id="traffic"
                                               placeholder="traffic" title="enter traffic to add.">
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <br>
                                            <button class="btn btn-lg btn-success" type="submit"><i
                                                    class="glyphicon glyphicon-ok-sign"></i> Add traffic
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="/jsp/common/Footer.jsp"/>
</body>
</html>
