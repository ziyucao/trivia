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
            top: 7.5rem;
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

<header class="navbar bg-primary" style="padding:15px 0">
    <section class="navbar-center">
        <a href="#" class="navbar-brand text-light" style="padding-left: 20px">TRIVIA</a>
    </section>
</header>

<body>
<div class="container">
    <div class="top">
        <div class="columns">
            <div class="column col-3">
                <div class="card" id="card1">
                    <div class="card-header" style="padding: 0.4rem;">
                        <div class="card-title h5" id="name1">Player1</div>
                        <div class="card-subtitle text-gray" id="coin1">Coins:</div>
                    </div>
                </div>
            </div>
            <div class="column col-3">
                <div class="card" id="card2">
                    <div class="card-header" style="padding: 0.4rem;">
                        <div class="card-title h5" id="name2">Player2</div>
                        <div class="card-subtitle text-gray" id="coin2">Coins:</div>
                    </div>
                </div>
            </div>
            <div class="column col-3">
                <div class="card" id="card3">
                    <div class="card-header" style="padding: 0.4rem;">
                        <div class="card-title h5" id="name3">Player3</div>
                        <div class="card-subtitle text-gray" id="coin3">Coins:</div>
                    </div>
                </div>
            </div>
            <div class="column col-3">
                <div class="card" id="card4">
                    <div class="card-header" style="padding: 0.4rem;">
                        <div class="card-title h5" id="name4">Player4</div>
                        <div class="card-subtitle text-gray" id="coin4">Coins:</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="card">
            <div class="centerParent">
                <div class="column col-8 col-mx-auto">
                    <button class="btn" id="rolling-btn" onClick="rolling()">Rolling</button>
                    <h5 id="rolling-result">0</h5>
                    <div id="question-sheet">
                        <h4 id="question-statement"></h4>
                        <div>
                            <form action="answer" method="post">
                                <div class="columns">
                                    <div class="col-6" style="padding:0 1rem">
                                        <div class="card">
                                            <label class="form-radio">
                                                <input type="radio" name="answer" id="option-a">
                                                <i class="form-icon"></i> A.
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-6" style="padding:0 1rem">
                                        <div class="card">
                                            <label class="form-radio">
                                                <input type="radio" name="answer" id="option-b">
                                                <i class="form-icon"></i> B.
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-6" style="padding:0 1rem">
                                        <div class="card">
                                            <label class="form-radio">
                                                <input type="radio" name="answer" id="option-c">
                                                <i class="form-icon"></i> C.
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-6" style="padding:0 1rem">
                                        <div class="card">
                                            <label class="form-radio">
                                                <input type="radio" name="answer" id="option-d">
                                                <i class="form-icon"></i> D.
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <p>test</p>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function $(Nid) {
        return document.getElementById(Nid);
    }

    var isRolled = "${isRolled}";
    if (isRolled === "true") {
        $("rolling-btn").style.display = "none";
        $("rolling-result").style.display = "inline";
        $("rolling-result").innerText = "${dice}"
        getQuestionSheet();
    } else {
        $("rolling-btn").style.display = "inline";
        $("rolling-result").style.display = "none";
        $("rolling-result").innerText = "${dice}"
    }

    function getQuestionSheet() {
        $("question-statement").innerText = "${question}";
        var optionA = $("option-a").getAttribute("innerText");
        $("option-a").innerText = optionA + "${optionA}";
        var optionB = $("option-b").getAttribute("innerText");
        $("option-b").innerText = optionB + "${optionB}";
        var optionC = $("option-c").getAttribute("innerText");
        $("option-c").innerText = optionC + "${optionC}";
        var optionD = $("option-d").getAttribute("innerText");
        $("option-d").innerText = optionD + "${optionD}";
    }

    if ("true" == "${answering1}") {
        setIsAnswering("card1");
    }
    if ("true" == "${answering2}") {
        setIsAnswering("card2");
    }
    if ("true" == "${answering3}") {
        setIsAnswering("card3");
    }
    if ("true" == "${answering4}") {
        setIsAnswering("card4");
    }


    function setIsAnswering(Nid) {
        $(Nid).setAttribute("style", "border-color: #3634d2;box-shadow: 0 0 0 .12rem rgba(87, 85, 217, .2);");
    }

    $("name1").innerText = "${name0}";
    $("name2").innerText = "${name1}";
    $("name3").innerText = "${name2}";
    $("name4").innerText = "${name3}";

    var COINS = "Coins:";
    $("coin1").innerText = COINS + "${coin0}";
    $("coin2").innerText = COINS + "${coin1}";
    $("coin3").innerText = COINS + "${coin2}";
    $("coin4").innerText = COINS + "${coin3}";

    function rolling() {
        var temp = document.createElement("form");
        temp.action = "rolling";
        temp.method = "post";
        temp.style.display = "none";
        var opt = document.createElement("textarea");
        opt.name = "name";
        opt.value = "${myName}";
        temp.appendChild(opt);
        document.body.appendChild(temp);
        temp.submit();
        return temp;
    }
</script>
</body>
</html>
