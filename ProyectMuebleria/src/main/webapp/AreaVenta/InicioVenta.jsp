<%-- 
    Document   : InicioVenta
    Created on : 26/08/2021, 10:28:12
    Author     : joel
--%>

<%@page import="ModeloVenta.ValidarSesionVenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio Venta</title>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <%ValidarSesionVenta.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarVenta.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col align-self-center">
                    <img class="img-fluid" src="${pageContext.request.contextPath}/resources/Venta3.jpg" alt="250">
                </div>
            </div>
        </div>

        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
