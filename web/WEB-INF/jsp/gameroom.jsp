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
                        <div class="card-title h5" id="name0">Player0</div>
                        <div class="card-subtitle text-gray" id="coin1">Coins:</div>
                    </div>
                </div>
            </div>
            <div class="column col-3">
                <div class="card" id="card2">
                    <div class="card-header" style="padding: 0.4rem;">
                        <div class="card-title h5" id="name1">Player1</div>
                        <div class="card-subtitle text-gray" id="coin2">Coins:</div>
                    </div>
                </div>
            </div>
            <div class="column col-3">
                <div class="card" id="card3">
                    <div class="card-header" style="padding: 0.4rem;">
                        <div class="card-title h5" id="name2">Player2</div>
                        <div class="card-subtitle text-gray" id="coin3">Coins:</div>
                    </div>
                </div>
            </div>
            <div class="column col-3">
                <div class="card" id="card4">
                    <div class="card-header" style="padding: 0.4rem;">
                        <div class="card-title h5" id="name3">Player3</div>
                        <div class="card-subtitle text-gray" id="coin4">Coins:</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="card">
            <div class="centerParent">
                <div class="column col-8 col-mx-auto" id="answering">
                    <button class="btn" id="rolling-btn" onClick="rolling()">Rolling</button>
                    <div id="question-sheet">
                        <h4 id="question-statement"></h4>
                        <div>
                            <form action="answer" method="post">
                                <div class="columns">
                                    <div class="col-6" style="padding:0 1rem">
                                        <div class="card" onclick="postAnswer('a')">
                                            <label class="form-radio">
                                                <input type="radio" name="answer">
                                                <i class="form-icon"></i>
                                                <p id="option-a">A.</p>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-6" style="padding:0 1rem">
                                        <div class="card" onclick="postAnswer('b')">
                                            <label class="form-radio">
                                                <input type="radio" name="answer">
                                                <i class="form-icon"></i>
                                                <p id="option-b">B.</p>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-6" style="padding:0 1rem">
                                        <div class="card" onclick="postAnswer('c')">
                                            <label class="form-radio">
                                                <input type="radio" name="answer">
                                                <i class="form-icon"></i>
                                                <p id="option-c">C.</p>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-6" style="padding:0 1rem">
                                        <div class="card" onclick="postAnswer('d')">
                                            <label class="form-radio">
                                                <input type="radio" name="answer">
                                                <i class="form-icon"></i>
                                                <p id="option-d">D.</p>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="column col-8 col-mx-auto" id="notAnswering">
                    <h3>等待其他玩家操作……</h3>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function $(Nid) {
        return document.getElementById(Nid);
    }

    switch ("${winnerId}") {
        case "0":
            alert("Winner is ${name0}");
            break;
        case "1":
            alert("Winner is ${name1}");
            break;
        case "2":
            alert("Winner is ${name2}");
            break;
        case "3":
            alert("Winner is ${name3}");
            break;
        default :
            break;
    }
    var isRolled = "${isRolled}";
    var myName = "${myName}";
    var myAnswering;
    if (myName == "${name0}") {
        myAnswering = "${answering0}"
        setMe("name0");
    }
    if (myName == "${name1}") {
        myAnswering = "${answering1}"
        setMe("name1");
    }
    if (myName == "${name2}") {
        myAnswering = "${answering2}"
        setMe("name2");
    }
    if (myName == "${name3}") {
        myAnswering = "${answering3}"
        setMe("name3");
    }
    if (myAnswering !== "1") {
        setInterval(post, 2000);
        $("answering").style.display = "none";
    } else {
        $("notAnswering").style.display = "none";
        if (isRolled === "1") {
            $("rolling-btn").style.display = "none";
            getQuestionSheet();
        } else {
            $("rolling-btn").style.display = "inline";
            $("question-sheet").style.display = "none";
        }
    }


    function getQuestionSheet() {
        $("question-statement").innerText = "${question}";
        $("option-a").innerText = "A." + "${optionA}";
        $("option-b").innerText = "B." + "${optionB}";
        $("option-c").innerText = "C." + "${optionC}";
        $("option-d").innerText = "D." + "${optionD}";
    }

    if ("1" == "${answering0}") {
        setIsAnswering("card1");
    }
    if ("1" == "${answering1}") {
        setIsAnswering("card2");
    }
    if ("1" == "${answering2}") {
        setIsAnswering("card3");
    }
    if ("1" == "${answering3}") {
        setIsAnswering("card4");
    }


    function setIsAnswering(Nid) {
        $(Nid).setAttribute("style", "border-color: #3634d2;box-shadow: 0 0 0 .12rem rgba(87, 85, 217, .2);");
    }

    function setMe(Nid) {
        $(Nid).setAttribute("class", "card-title h5 text-success");
    }

    $("name0").innerText = "${name0}";
    $("name1").innerText = "${name1}";
    $("name2").innerText = "${name2}";
    $("name3").innerText = "${name3}";

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

    function postAnswer(myOption) {
        var temp = document.createElement("form");
        temp.action = "answer";
        temp.method = "post";
        temp.style.display = "none";
        var opt1 = document.createElement("textarea");
        var opt2 = document.createElement("textarea");
        var opt3 = document.createElement("textarea");
        opt1.name = "name";
        opt1.value = "${myName}";
        opt2.name = "questionId";
        opt2.value = "${questionId}";
        opt3.name = "option";
        opt3.value = myOption;
        temp.appendChild(opt1);
        temp.appendChild(opt2);
        temp.appendChild(opt3);
        document.body.appendChild(temp);
        temp.submit();
        return temp;
    }

    function post() {
        var temp = document.createElement("form");
        temp.action = "update";
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
