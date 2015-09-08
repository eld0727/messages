<%@ page import="otts.test.work.util.ButtonColor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/xhr.js"></script>
</head>
<body>
    <div>
        <div>
            User
            <select id="add-visit-page-user-select">
                <%
                    Integer usersCount = (Integer) request.getAttribute("usersCount");
                    for (int i = 1; i <= usersCount; i++) { %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select>
            visit a page
            <button id="add-visit-page">add</button>
        </div>
    </div>
    <div>
        <div>
            User
            <select id="add-any-button-user-select">
                <%
                    for (int i = 1; i <= usersCount; i++) { %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select>
            clicks any button
            <button id="add-any-button">add</button>
        </div>
    </div>
    <div>
        <div>
            User
            <select id="add-color-user-select">
                <%
                    for (int i = 1; i <= usersCount; i++) { %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select>
            clicks
            <select id="add-color-color-select">
                <%
                    ButtonColor[] colors = ButtonColor.values();
                    for (int i = 0; i < colors.length; i++) {
                        ButtonColor color = colors[i];
                %>
                <option value="<%=color.getName()%>"><%=color.getName().toLowerCase()%></option>
                <%
                    }
                %>
            </select>
            button
            <button id="add-color">add</button>
        </div>
    </div>
    <script type="text/javascript">
        (function() {
            var addVisitPageBtn = document.getElementById("add-visit-page");
            addVisitPageBtn.addEventListener("click", function (event) {
                var addVisitPageUserSelect = document.getElementById("add-visit-page-user-select");
                window.xhr({
                    method: "POST",
                    url: "${pageContext.request.contextPath}/messages/visit",
                    callback: function () {
                        alert("event was successfully added");
                    },
                    data: {
                        id: +addVisitPageUserSelect.value
                    }
                });
            });

            var addAnyButtonBtn = document.getElementById("add-any-button");
            addAnyButtonBtn.addEventListener("click", function (event) {
                var addAnyButtonUserSelect = document.getElementById("add-any-button-user-select");
                window.xhr({
                    method: "POST",
                    url: "${pageContext.request.contextPath}/messages/button/click/any",
                    callback: function () {
                        alert("event was successfully added");
                    },
                    data: {
                        id: +addAnyButtonUserSelect.value
                    }
                });
            });

            var addColorBtn = document.getElementById("add-color");
            addColorBtn.addEventListener("click", function (event) {
                var addColorUserSelect = document.getElementById("add-color-user-select");
                var addColorColorSelect = document.getElementById("add-color-color-select");
                window.xhr({
                    method: "POST",
                    url: "${pageContext.request.contextPath}/messages/button/click/color",
                    callback: function () {
                        alert("event was successfully added");
                    },
                    data: {
                        id: +addColorUserSelect.value,
                        color: addColorColorSelect.value
                    }
                });
            });

        })();
    </script>
</body>
</html>