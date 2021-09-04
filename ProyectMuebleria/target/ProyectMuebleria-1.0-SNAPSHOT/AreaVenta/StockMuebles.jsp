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
        <title>Registrar Venta</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/RegistrarVenta.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>   
    </head>
    <body>
        <jsp:include page="/NavBars/NavBarVenta.jsp"/>

        <div class="container">
            <div class="row justify-content-around mt-5">
                <div class="col-12">
                    <div class="row justify-content-first">
                        <div class="col-5 text-center">
                            <label for="inputFiltro">Busqueda por Filtro:</label>
                        </div>
                        <div class="col-5">
                            <input class="form-control" id="inputFiltro" type="text" placeholder="Filtro">
                        </div>
                    </div>
                        
                    <div class="table-data">
                        <table class="table table-striped table-bordered mt-5">
                            <thead class="table-dark">
                                <tr>
                                    <th class="th-sm">Id</th>
                                    <th class="th-sm">Nombre Mueble</th>
                                    <th class="th-sm">Precio</th>
                                </tr>
                            </thead>
                            <tbody id="tablaFiltro">
                                <c:forEach items="${ensambles}" var="ensamble">
                                    <tr>
                                        <td>${ensamble.id}</td>
                                        <td>${ensamble.nombre}</td>
                                        <td>${ensamble.precio}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Nombre Mueble</th>
                                    <th>Precio</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../Extra/JS.jsp"/>
        <script src="${pageContext.request.contextPath}/JSExtra/FuncionFiltro.js"></script>
    </body>
</html>
