<%-- 
    Document   : StockPiezas
    Created on : 26/08/2021, 22:25:26
    Author     : joel
--%>

<%@page import="ModeloVenta.ValidarSesionVenta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compras de Cliente Por Intervalo</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/VentasDelDia.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <%ValidarSesionVenta.validarSesion(request, response);%>
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
                                    <th class="th-sm">Fecha de Compra</th>
                                    <th class="th-sm">Precio</th>
                                    <th class="th-sm">NIT del Cliente</th>
                                    <th class="th-sm">Nombre Cliente</th>
                                    <th class="th-sm">Direccion</th>
                                    <th class="th-sm">Municipio</th>
                                    <th class="th-sm">Departamento</th>
                                </tr>
                            </thead>
                            <tbody id="tablaFiltro">
                                <c:forEach items="${compras}" var="compra">
                                    <tr>
                                        <td>${compra.idFactura}</td>
                                        <td>${compra.fechaCompra}</td>
                                        <td>${compra.precioCompra}</td>
                                        <td>${compra.NIT}</td>
                                        <td>${compra.nombre}</td>
                                        <td>${compra.direccion}</td>
                                        <td>${compra.municipio}</td>
                                        <td>${compra.departamento}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Fecha de Compra</th>
                                    <th>Precio</th>
                                    <th>NIT del Cliente</th>
                                    <th>Nombre Cliente</th>
                                    <th>Direccion</th>
                                    <th>Municipio</th>
                                    <th>Departamento</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../Extra/JS.jsp"/>

    <script src="${pageContext.request.contextPath}/JSExtra/FuncionFiltro.js"></script>
</body>
</html>
