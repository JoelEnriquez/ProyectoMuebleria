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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/RegistrarVenta.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <%ValidarSesionVenta.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarVenta.jsp"/>

        <div class="container">
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
                        <table class="table table-striped table-bordered mt-5">
                            <thead class="table-dark">
                                <tr>
                                    <th class="th-sm">Id</th>
                                    <th class="th-sm">Fecha de Compra</th>
                                    <th class="th-sm">Precio</th>
                                    <th class="th-sm">Detalles Factura</th>
                                </tr>
                            </thead>
                            <tbody id="tablaFiltro">
                                <c:forEach items="${compras}" var="compra">
                                    <tr>
                                        <td>${compra.idFactura}</td>
                                        <td>${compra.fechaCompra}</td>
                                        <td>${compra.precioCompra}</td>
                                        <td>
                                            <a class="btn btn-info" href="${pageContext.request.contextPath}/ComprasCliente?id=${compra.idFactura}">
                                                Detalles
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Fecha de Compra</th>
                                    <th>Precio</th>
                                    <th>Detalles Factura</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
                <div class="col-3 align-self-center">
                    <div class="row">
                        <c:if test="${error!=null}"> <p class="alert alert-danger" style="text-align: center">${error}</p></c:if>
                        <form action="${pageContext.request.contextPath}/ComprasCliente" method="POST">
                            <h3 style="text-align: center">Compras de Cliente Por Intervalo</h3>
                            <div class="input-group mb-3 justify-content-center">
                                <div class="col-auto">
                                    <input style="text-align: center" type="text" class="form-control" name="NIT" id="inputNIT" placeholder="NIT del Cliente" required>
                                </div>
                            </div>
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
                    <div class="row mt-5">
                        <h3 style="text-align: center">Datos del Cliente</h3>
                        <form>
                            <fieldset disabled class="datos_cliente">
                                <div class="form-group">
                                    <label for="disabledNIT">NIT</label>
                                    <input type="text" value="${cliente.NIT}" name="NIT" id="disabledNIT" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="disableName">Nombre</label>
                                    <input type="text" value="${cliente.nombre}" name="nombre" id="disabledName" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="disabledDireccion">Direccion</label>
                                    <input type="text" value="${cliente.direccion}" name="direccion" id="disabledDireccion" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="disabledNIT">Municipio</label>
                                    <input type="text" value="${cliente.municipio}"  name="municipio" id="disabledMunicipio" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="disabledDepartamento">Departamento</label>
                                    <input type="text" value="${cliente.departamento}" name="departamento" id="disabledDepartamento" class="form-control">
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../Extra/JS.jsp"/>

        <script src="${pageContext.request.contextPath}/JSExtra/FuncionFiltro.js"></script>
    </body>
</html>
