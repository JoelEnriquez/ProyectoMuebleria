<%-- 
    Document   : StockPiezas
    Created on : 26/08/2021, 22:25:26
    Author     : joel
--%>

<%@page import="ModeloFinanciera.ValidarSesionFinanciera"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte de Ganancias</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/ReportesFinanciera.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <%ValidarSesionFinanciera.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFinanciera.jsp"/>

        <div class="container-fluid">
            <div class="row justify-content-around mt-5">
                <div class="col-9">
                    <div class="row justify-content-first">
                        <div class="col-5 text-center">
                            <label for="inputFiltro">Busqueda por Filtro:</label>
                        </div>
                        <div class="col-5">
                            <input class="form-control" id="inputFiltro" type="text" placeholder="Filtro">
                        </div>
                    </div>

                    <div class="table-data">
                        <table class="table table-striped table-bordered mt-5" id="tabla_exportar">
                            <thead class="table-dark">
                                <tr>
                                    <th class="th-sm">Id Ensamble</th>
                                    <th class="th-sm">Nombre de Mueble</th>
                                    <th class="th-sm">Fecha Compra</th>
                                    <th class="th-sm">Ingresos por Venta</th>
                                    <th class="th-sm">Costos por Fabricacion</th>
                                    <th class="th-sm">Costos por Devolucion</th>
                                    <th class="th-sm">Ganancias</th>
                                    <th class="th-sm">Ingreso Total</th>
                                    <th class="th-sm">Ganancia Total</th>
                                </tr>
                            </thead>
                            <tbody id="tablaFiltro">
                                <c:forEach items="${listaGanancias}" var="listaGanancia">
                                    <tr>
                                        <td>${listaGanancia.idEnsamble}</td>
                                        <td>${listaGanancia.nombreMueble}</td>
                                        <td>${listaGanancia.fechaCompra}</td>
                                        <td>${listaGanancia.ingresosVenta}</td>
                                        <td>${listaGanancia.costoFabricacion}</td>
                                        <td>${listaGanancia.perdidasDevolucion}</td>
                                        <td>${listaGanancia.ganancia}</td>
                                        <td class="bg-info text-dark">${listaGanancia.ingresoTotal}</td>
                                        <td class="bg-info text-dark">${listaGanancia.gananciaTotal}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id Ensamble</th>
                                    <th>Nombre de Mueble</th>
                                    <th>Fecha Compra</th>
                                    <th>Ingresos por Venta</th>
                                    <th>Costos por Fabricacion</th>
                                    <th>Costos por Devolucion</th>
                                    <th>Ganancias</th>
                                    <th>Ingreso Total</th>
                                    <th>Ganancia Total</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
                <div class="col-3 align-self-center">
                    <div class="row">
                        <c:if test="${error!=null}"> <p class="alert alert-danger">${error}</p></c:if>
                        <form action="${pageContext.request.contextPath}/ReporteGanancias" method="POST">
                            <h3>Ganancias por Intervalo</h3>
                            <div class="input-group mb-3 justify-content-around">
                                <div class="col-auto">
                                    <label for="date1" class="col-form-label">Fecha 1:</label>
                                </div>
                                <div class="col-auto">
                                    <input type="date" name="fecha_1" id="date1" class="form-control">
                                </div>
                            </div>
                            <div class="input-group mb-3 justify-content-around">
                                <div class="col-auto">
                                    <label for="date2" class="col-form-label">Fecha 2:</label>
                                </div>
                                <div class="col-auto">
                                    <input type="date" name="fecha_2" id="date2" class="form-control">
                                </div>
                            </div>
                            <div class="input-group mb-3 mt-2 justify-content-center">
                                <div class="col-auto">
                                    <button type="submit" class="btn btn-primary active">Buscar</button>
                                </div>
                            </div>   
                        </form>
                    </div>
                    <div class="row">
                        <button id="boton_exportar" class="btn btn-outline-dark btn-lg mt-5">Exportar Tabla</button>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../Extra/JS.jsp"/>

        <script src="${pageContext.request.contextPath}/JSExtra/FuncionFiltro.js"></script>
        <script src="${pageContext.request.contextPath}/JSExtra/ExportadorTablaCSV.js"></script>
        <script src="${pageContext.request.contextPath}/JSExtra/ExportarCSV.js"></script>
    </body>
</html>
