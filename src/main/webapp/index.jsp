<%@ page import="org.example.lab2.beans.ResultBean" %>
<%@ page import="org.example.lab2.beans.ResultStorageBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/css/style.css">
    <link rel="icon" type="image/x-icon" href="static/images/favicon.ico">
    <title>Lab2</title>
</head>
<body>
<!-- Шапка -->
<div class="header">
    <div class="part" id="namepart">Стариков Арсений</div>
    <div class="part" id="labnamepart">Лабораторная работа 2</div>
    <div class="part" id="variantpart">Вариант: 1704</div>
</div>

<!-- Контент -->
<div class="content">
    <div class="container">
        <form id="form" action="${pageContext.request.contextPath}/controller" method="POST">
            <label class="value-label"> Выберите значение X:
                <select name="xvalue" id="xvalue" required>
                    <option value="-3" selected>-3</option>
                    <option value="-2">-2</option>
                    <option value="-1">-1</option>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </label> <br>
            <label>Введите Y: <input type="text" name="yvalue" id="yvalue" placeholder="от -3 до 3"></label> <br>
            <label>Введите R: <input type="text" name="rvalue" id="rvalue" placeholder="от 2 до 5"></label> <br>
            <input type="submit"></input>
        </form>
    </div>

    <div class="container">
        <canvas id="graph"> </canvas>
    </div>

    <div class="container">
        <table>
            <caption>
                Результаты попаданий
            </caption>
            <thead>
            <tr>
                <th scope="col">R</th>
                <th scope="col">X</th>
                <th scope="col">Y</th>
                <th scope="col">Попадание</th>
                <th scope="col">Время начала</th>
                <th scope="col">Время выполнения, с.</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>

    </div>
</div>

<div id="graphPopup" class="popup">
    <div class="popup-content">
        <span class="popup-close">&times;</span>
        <p><span id="popupTitle"></span></p>
        <p><span id="popupResult"></span></p>
    </div>
</div>
<script>
    window.dots = [
        <%
            ArrayList<ResultBean> results = new ArrayList<>();
            Object storage = session.getAttribute("resultStorage");
            if (storage != null && storage instanceof ResultStorageBean) {
                ResultStorageBean resultStorage = (ResultStorageBean) storage;
                results = resultStorage.getResultList();
            }
            for (ResultBean res: results) {
        %>
        {
            r: <%= res.getUserValueBean().getR() %>,
            x: <%= res.getUserValueBean().getX() %>,
            y: <%= res.getUserValueBean().getY() %>,
            isHit: "<%= res.getHit()%>",
            startTime: "<%= res.getStartTime() != null ? res.getStartTime() : "" %>",
            execTime: "<%=String.format("%.4f",(res.getExecutionTime()/1_000_000.0)).replace(',', '.') %>"
        },
        <%
        }
        %>
    ]
</script>
<script src="static/js/notification.js"></script>
<script src="static/js/fillTable.js"></script>
<script src="static/js/validation.js"></script>
<script src="static/js/input.js"></script>
<script src="static/js/submitForm.js"></script>
<script src="static/js/graph.js"></script>
<script src="static/js/clickGraphHandler.js"></script>
<script src="static/js/drawDots.js"></script>
</body>
</html>
