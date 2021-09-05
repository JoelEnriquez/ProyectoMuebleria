<%-- 
    Document   : StockPiezas
    Created on : 26/08/2021, 22:25:26
    Author     : joel
--%>

<%@page import="ModeloFabrica.ValidarSesionFabrica"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock de Piezas</title>
        <link rel="stylesheet" href="CSSExtra/StockPiezas.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>   
    </head>
    <body>
        <% ValidarSesionFabrica.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFabrica.jsp"/>

        <div class="container">

            <div class="form-stock row justify-content-around mt-5">
                <div class="col-4">
                    <h4>Ordenar por Stock</h4>
                </div>
                <div class="col-4">
                    <form action="${pageContext.request.contextPath}/ConsultarInventario" method="POST">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="ordenar-stock" id="exampleRadios1" value="DESC" checked>
                            <label class="form-check-label" for="exampleRadios1">
                                Mayor a menor
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="ordenar-stock" id="exampleRadios2" value="ASC">
                            <label class="form-check-label" for="exampleRadios2">
                                Menor a mayor
                            </label>
                        </div>
                        <button type="submit" class="btn btn-success">Ordenar</button>
                    </form>
                </div>
            </div>

            <div class="table-data">
                <table class="table table-striped table-bordered mt-5">
                    <thead class="table-dark">
                        <tr>
                            <th class="th-sm">Id</th>
                            <th class="th-sm">Nombre Pieza</th>
                            <th class="th-sm">Precio</th>
                            <th class="th-sm">Stock</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${stockPiezas}" var="stockPieza">
                            <tr>
                                <td>${stockPieza.id}</td>
                                <td>${stockPieza.tipoPieza}</td>
                                <td>${stockPieza.precio}</td>
                                <td <c:if test="${stockPieza.cantidadStock<10}">class="bg-danger text-white"</c:if>>
                                    ${stockPieza.cantidadStock}
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Id</th>
                            <th>Nombre Pieza</th>
                            <th>Precio</th>
                            <th>Stock</th>
                        </tr>
                    </tfoot>
                </table>
            </div>



        </div>
        <jsp:include page="../Extra/JS.jsp"/>


    </body>
</html>
