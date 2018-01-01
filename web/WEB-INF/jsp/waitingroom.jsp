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
        .content {
            padding: 20px 20px;
            position: absolute;
            width: 100%;
            top: 3rem;
            bottom: 0;
            left: 0;
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
<body>
<header class="navbar bg-primary" style="padding:15px 0">
    <section class="navbar-center">
        <a href="#" class="navbar-brand text-light" style="padding-left: 20px">TRIVIA</a>
    </section>
</header>

<div class="content">
    <div class="centerParent">
        <div class="container">
            <div class="columns">
                <div style="width: 100%; padding: 10px; text-align: center">
                    <h1>Hello, ${name}.</h1> <br>
                </div>
                <div class="column col-3 col-sm-6 col-lg-4 col-mx-auto">
                    <div class="card" style="padding: 20px; background: #e5e5f9">
                        <div class="loading loading-lg"></div>
                        <h4><b>MATCHING...</b></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    setInterval(post, 2000);

    function post() {
        var temp = document.createElement("form");
        temp.action = "gameroom";
        temp.method = "post";
        temp.style.display = "none";
        var opt = document.createElement("textarea");
        opt.name = "name";
        opt.value = "${name}";
        temp.appendChild(opt);
        document.body.appendChild(temp);
        temp.submit();
        return temp;
    }

</script>
</body>
</html>
