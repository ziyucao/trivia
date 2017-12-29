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
        .top {
            padding: 20px 12px 0;
            height: 60px;
        }
        .content {
            padding: 20px 20px;
            position: absolute;
            width: 100%;
            top: 164px;
            bottom: 0px;
            left: 0px;
            text-align: center;
        }
        .centerParent {
            display: flex;
            display: -webkit-flex;
            height: 100%;
            align-items: center; /*指定垂直居中*/
        }
    </style>
</head>

<header class="navbar bg-primary" style="padding:15px 0px">
    <section class="navbar-center">
        <a href="#" class="navbar-brand text-light" style="padding-left: 20px">TRIVIA</a>
    </section>
</header>

<div class="container">
    <div class="top">
        <div class="columns">
            <div class="column col-3">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title h5">Player1</div>
                        <div class="card-subtitle text-gray">Coins:</div>
                    </div>
                </div>
            </div>
            <div class="column col-3">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title h5">Player2</div>
                        <div class="card-subtitle text-gray">Coins:</div>
                    </div>
                </div>
            </div>
            <div class="column col-3">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title h5">Player3</div>
                        <div class="card-subtitle text-gray">Coins:</div>
                    </div>
                </div>
            </div>
            <div class="column col-3">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title h5">Player4</div>
                        <div class="card-subtitle text-gray">Coins:</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="card">
            <div class="centerParent">
                <div class="columns" style="width: 100%">
                    <div class="column col-4 col-mx-auto">
                        <h1>Hello, ${name}.</h1> <br>
                        <h4><b>MATCHING...</b></h4>
                        <div class="loading loading-lg"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
