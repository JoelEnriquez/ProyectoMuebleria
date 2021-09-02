<%-- 
    Document   : InicioVenta
    Created on : 26/08/2021, 10:28:12
    Author     : joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio Venta</title>
        <link rel="stylesheet" href="../CSSExtra/SolicitarNIT.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <jsp:include page="/NavBars/NavBarVenta.jsp"/>
        <div class="container-fluid">
            <div class="row align-items-center justify-content-around">
                <div class="col-6">
                    <h3>Registrar Cliente</h3>
                    <a class="btn btn-dark">Registrar</a>
                </div>
                <div class="col-6">
                    <h3>Ingresar con NIT</h3>
                    <a class="btn btn-success">Ingresar</a>
                </div>
            </div>
        </div>

        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
