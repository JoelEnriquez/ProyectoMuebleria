<%-- 
    Document   : StockPiezas
    Created on : 26/08/2021, 22:25:26
    Author     : joel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock de Piezas</title>
        <link rel="stylesheet" href="../CSSExtra/StockPiezas.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>   
    </head>
    <body>
        <jsp:include page="/NavBars/NavBarFabrica.jsp"/>

        <div class="container">



            <div class="table-data">
                <table class="table table-striped table-bordered mt-5">
                    <thead class="table-dark">
                        <tr>
                            <th class="th-sm">Nombre Pieza</th>
                            <th class="th-sm">Precio</th>
                            <th class="th-sm">Stock</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${stockPiezas}" var="stockPieza">
                            <tr>
                                <td>${stockPieza.tipoPieza}</td>
                                <td>${stockPieza.precio}</td>
                                <td>${stockPieza.cantidadStock}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Nombre Pieza</th>
                            <th>Precio</th>
                            <th>Stock</th>
                        </tr>
                    </tfoot>
                </table>
            </div>

            <div class="form-stock row">
                <div class="col">
                    Form
                </div>
            </div>

        </div>
        <jsp:include page="../Extra/JS.jsp"/>


    </body>
</html>
