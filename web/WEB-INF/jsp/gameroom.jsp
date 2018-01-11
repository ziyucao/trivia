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

        .count_down_10 {
            width: 100%;
            transition: width 10s linear;
            -moz-transition: width 10s linear;
            -webkit-transition: width 10s linear;
        }

        .count_down_25 {
            width: 100%;
            transition: width 25s linear;
            -moz-transition: width 25s linear;
            -webkit-transition: width 25s linear;
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
                    <button class="btn" style="margin-bottom: 1.5rem" id="rolling-btn" onClick="rolling()">Rolling
                    </button>
                    <div id="question-sheet">
                        <h4 id="question-statement"></h4>
                        <div>
                            <form action="answer" method="post">
                                <div class="columns">
                                    <div class="col-6" style="padding:0.5rem 1rem">
                                        <div class="btn btn-lg" style="width: 100%" name="answer" id="option-a"
                                             onclick="postAnswer('a')">

                                        </div>
                                    </div>
                                    <div class="col-6" style="padding:0.5rem 1rem">
                                        <div class="btn btn-lg" style="width: 100%" name="answer" id="option-b"
                                             onclick="postAnswer('b')">

                                        </div>
                                    </div>
                                    <div class="col-6" style="padding:0.5rem 1rem">
                                        <div class="btn btn-lg" style="width: 100%" name="answer" id="option-c"
                                             onclick="postAnswer('c')">

                                        </div>
                                    </div>
                                    <div class="col-6" style="padding:0.5rem 1rem">
                                        <div class="btn btn-lg" style="width: 100%" name="answer" id="option-d"
                                             onclick="postAnswer('d')">

                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                    <div class="column col-12 col-mx-auto" style="width:99%">
                        <div class="bar bar-sm">
                            <div id="count-down" class="bar-item count_down_10" role="progressbar" style="width:0"
                                 aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
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

    if ("1" === "${isEnd}") {
        alert("You lose!");
        end();
    }
    if ("2" === "${isEnd}") {
        alert("You win!");
        end();
    }

    $("name0").innerText = "${name0}";
    $("name1").innerText = "${name1}";
    $("name2").innerText = "${name2}";
    $("name3").innerText = "${name3}";

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
            $("count-down").setAttribute("class", "bar-item count_down_25");
            var t0 = window.setTimeout(countDown, 100);
            var t1 = window.setTimeout(timeout, 25000);
            $("rolling-btn").style.display = "none";
            getQuestionSheet();
        } else {
            var t2 = window.setTimeout(countDown, 100);
            var t3 = window.setTimeout(rolling, 10000);
            $("rolling-btn").style.display = "inline";
            $("question-sheet").style.display = "none";
        }
    }

    function countDown() {
        $("count-down").style.width = "100%"
    }


    function getQuestionSheet() {
        $("question-statement").innerText = "Q:${question}";
        $("option-a").innerText = "A." + "${optionA}";
        $("option-b").innerText = "B." + "${optionB}";
        $("option-c").innerText = "C." + "${optionC}";
        $("option-d").innerText = "D." + "${optionD}";
    }

    if ("1" == "${punished0}" && "1" == "${answering0}") {
        setIsPunishedAndSetIsAnswering("card1");
    } else {
        if ("1" == "${punished0}") {
            setIsPunished("card1");
        }
        if ("1" == "${answering0}") {
            setIsAnswering("card1");
        }
    }
    if ("1" == "${punished1}" && "1" == "${answering1}") {
        setIsPunishedAndSetIsAnswering("card2");
    } else {
        if ("1" == "${punished1}") {
            setIsPunished("card2");
        }

        if ("1" == "${answering1}") {
            setIsAnswering("card2");
        }
    }
    if ("1" == "${punished2}" && "1" == "${answering2}") {
        setIsPunishedAndSetIsAnswering("card3");
    } else {
        if ("1" == "${punished2}") {
            setIsPunished("card3");
        }
        if ("1" == "${answering2}") {
            setIsAnswering("card3");
        }
    }
    if ("1" == "${punished3}" && "1" == "${answering3}") {
        setIsPunishedAndSetIsAnswering("card4");
    } else {
        if ("1" == "${punished3}") {
            setIsPunished("card4");
        }

        if ("1" == "${answering3}") {
            setIsAnswering("card4");
        }
    }

    function setIsPunished(Nid) {
        $(Nid).setAttribute("style", "background:repeating-linear-gradient(45deg, #ffcccc 0,#ffcccc 5px,#fff 5px,#fff 20px);border-color: #e85600;box-shadow: 0 0 0 .12rem rgba(232, 86, 0, .2);");
    }

    function setIsAnswering(Nid) {
        $(Nid).setAttribute("style", "border-color: #3634d2;box-shadow: 0 0 0 .12rem rgba(87, 85, 217, .2);");
    }

    function setIsPunishedAndSetIsAnswering(Nid) {
        $(Nid).setAttribute("style", "background:repeating-linear-gradient(45deg, #ffcccc 0,#ffcccc 5px,#fff 5px,#fff 20px);border-color: #3634d2;box-shadow: 0 0 0 .12rem rgba(87, 85, 217, .2);");
    }

    function setMe(Nid) {
        $(Nid).innerHTML = "<span class='badge' data-badge=' Me '>" + myName + "</span>";
    }


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


    function timeout() {
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
        opt3.value = "e";
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

    function end() {
        var temp = document.createElement("form");
        temp.action = "end";
        temp.method = "post";
        temp.style.display = "none";
        document.body.appendChild(temp);
        temp.submit();
        return temp;
    }

</script>
</body>
</html>
