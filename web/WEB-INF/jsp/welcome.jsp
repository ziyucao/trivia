<%--
  Created by IntelliJ IDEA.
  User: CaoZiyu
  Date: 2017/12/27
  Time: 下午4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://unpkg.com/spectre.css/dist/spectre.min.css">
    <title>Trivia</title>
    <style>
        .welcome {
            padding-left: 20px;
            padding-right: 20px;
            padding-top: 60px;
            padding-bottom: 120px;
            text-align: center;
            width: 100%;
        }

        .centerParent {
            display: flex;
            display: -webkit-flex;
            height: 100%;
            align-items: center; /*指定垂直居中*/
        }
    </style>
</head>
<body>
<header class="navbar bg-primary" style="padding:15px 0px">
    <section class="col-3 col-sm-1 col-md-2 col-lg-3">
    </section>
    <section class="navbar-center">
        <a href="#" class="navbar-brand text-light" style="">TRIVIA</a>
    </section>
    <section class="navbar-section">
    </section>

</header>
<div class="container" style="height: 90%">
    <div class="centerParent">
        <div class="welcome">
            <h1><b>Welcome to Trivia !</b></h1>
            <form action="waitingroom" method="post">
                <div class="columns">
                    <div class="col-4 col-xl-4 col-lg-4 col-md-3 col-sm-1"></div>
                    <div class="input-group col-4 col-xl-4 col-lg-4 col-md-6 col-sm-10">
                        <span class="input-group-addon" style="background: #f1f1fc">My Name is</span>
                        <input type="text" name="name" class="form-input input-lg" placeholder="...">
                        <button class="btn btn-lg btn-primary input-group-btn" type="submit" id="play"
                                onClick="login()">Play
                        </button>
                    </div>
                    <div class="col-4 col-xl-4 col-lg-4 col-md-3 col-sm-1"></div>
                </div>
                <p class="form-input-hint text-error" id="input-hint">${errorMessage}</p>
            </form>
        </div>
    </div>
</div>
<script>
    function login() {
        document.getElementById("play").setAttribute("class", "btn  btn-lg btn-primary input-group-btn loading");
    }
</script>
</body>
</html>
