<%@ page import="otts.test.work.dto.MessageStoreState" %>
<%@ page import="otts.test.work.util.ButtonColor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/xhr.js"></script>
    <style>
        html, body{height:100%;}

        .container{
            width:100%;
            height:100%;
            display: table;
        }
        #expectedMessages {
            width:30%;
            border-right: 3px solid;
            display: table-cell;
        }
        #buttons {
            width:40%;
            border-right: 3px solid;
            display: table-cell;
        }
        #finishedMessages {width: 30%;}
        button.color-btn {
            width: 100px;
            height: 30px;
            display: table-cell;
        }
    </style>
</head>
<body>
    <div class="container">
        <div id="expectedMessages">
            <div>expected values</div>
            <div id="expectedMessagesContent">
                <%
                    MessageStoreState messageStoreState = (MessageStoreState)request.getAttribute("messagesStore");
                    StringBuilder expectedMessages = new StringBuilder();
                    for (String m : messageStoreState.getExpectedMessages()) {
                        expectedMessages.append(m).append("<br/>");
                    }
                %>
                <%=expectedMessages.toString()%>
            </div>
        </div>
        <div id="buttons">
            <%
                ButtonColor[] colors = ButtonColor.values();
                for (int i = 0; i < colors.length; i++) {
                    ButtonColor color = colors[i];
            %>
            <button value="<%=color.getName()%>" class="color-btn" style="background-color: <%=color.getColor()%>">
                <%=color.getName().toLowerCase()%>
            </button>
            <%
                }
            %>
        </div>
        <div id="finishedMessages">
            <div>finished values</div>
            <div id="finishedMessagesContent">
                <%
                    StringBuilder finishedMessages = new StringBuilder();
                    for (String m : messageStoreState.getFinishedMessages()) {
                        finishedMessages.append(m).append("<br/>");
                    }
                %>
                <%=finishedMessages.toString()%>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        (function() {
            var btns = document.getElementsByClassName("color-btn");
            for (var i = 0; i < btns.length; i++) {
                var btn = btns[i];
                btn.addEventListener("click", function (event) {
                    window.xhr({
                        method: "POST",
                        url: "${pageContext.request.contextPath}/buttons/click",
                        data: {
                            id: <%=request.getAttribute("userId")%>,
                            color: this.value
                        }
                    });
                });
            }

            var updateList = function () {
                window.xhr({
                    method: "GET",
                    url: "${pageContext.request.contextPath}/messages",
                    callback: function (response) {
                        var expected = response.expectedMessages;
                        var expectedString = "";
                        for (var i = 0; i < expected.length; i++) {
                            var expectedMessage = expected[i];
                            expectedString += expectedMessage + "<br/>";
                        }
                        document.getElementById("expectedMessagesContent").innerHTML = expectedString;

                        var finished = response.finishedMessages;
                        var finishedString = "";
                        for (var i = 0; i < finished.length; i++) {
                            var finishedMessage = finished[i];
                            finishedString += finishedMessage + "<br/>";
                        }
                        document.getElementById("finishedMessagesContent").innerHTML = finishedString;
                        setTimeout(updateList, 1000);
                    }
                });
            };

            updateList();

            var checkAlerts = function () {
                window.xhr({
                    method: "GET",
                    url: "${pageContext.request.contextPath}/alerts",
                    callback: function (alerts) {
                        for (var i = 0; i < alerts.length; i++) {
                            alert(alerts[i].message);
                        }
                        setTimeout(checkAlerts, 250);
                    }
                });
            };
            checkAlerts();
        })();
    </script>
</body>
</html>