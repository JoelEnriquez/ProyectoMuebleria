<%-- 
    Document   : InicioFabrica
    Created on : 24/08/2021, 17:44:12
    Author     : joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio Fabrica</title>
        <jsp:include page="/Extra/CSS.jsp"/>
    </head>
    <body>
        <jsp:include page="/NavBars/NavBarFabrica.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col">
                    <img src="${pageContext.request.contextPath}/resources/Foto Fabrica.jpg" alt="150">
                </div>
            </div>
        </div>
        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
