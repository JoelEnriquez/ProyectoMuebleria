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
            <c:if test="${error!=null}"> <p class="alert alert-danger" style="text-align: center">${error}</p></c:if>

                <div class="row justify-content-around mt-5">
                    <div class="col-5">
                        <div class="table-data">
                            <table class="table table-striped table-bordered mt-5">
                                <thead class="table-dark">
                                    <tr>
                                        <th class="th-sm">Id</th>
                                        <th class="th-sm">Nombre Mueble</th>
                                        <th class="th-sm">Precio</th>
                                    </tr>
                                </thead>
                                <tbody>
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
                <div class="div-registro col-5 align-self-center p-3" style="background: #777a95;">
                    <h3>Generar Compra</h3>
                    <div class="row">
                        <div class="table-factura">
                            <table class="table table-success table-bordered mt-2 text-center">
                                <thead class="table-dark">
                                    <tr>
                                        <th class="th-sm">Id</th>
                                        <th class="th-sm">Nombre Mueble</th>
                                        <th class="th-sm">Precio</th>
                                        <th class="th-sm"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${carrito}" var="ensamble">
                                        <tr class="fila_elementos">
                                            <td>${ensamble.id}</td>
                                            <td>${ensamble.nombre}</td>
                                            <td>${ensamble.precio}</td>
                                            <td><a class="btn btn-danger" href="${pageContext.request.contextPath}/RegistrarVenta?eliminarId=${ensamble.id}">
                                                    Cancelar
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre Mueble</th>
                                        <th>Precio</th>
                                        <th></th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <form action="${pageContext.request.contextPath}/RegistrarVenta" method="POST">
                            <div class="input-group mb-3">
                                <div class="input-group mb-3 justify-content-between">
                                    <label class="input-group-text" for="id_mueble">Id Ensamble</label>
                                    <div class="col-sm-9">
                                        <input style="text-align: center" type="number" min="1" class="form-control" name="id_mueble" id="id_mueble" placeholder="Ingrese Id">
                                    </div>
                                </div>
                                <div class="input-group">
                                    <div class="input-group justify-content-center">
                                        <button type="submit" class="btn btn-light">Agregar al Carrito</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="row mt-4">
                        <div class="input-group">
                            <div class="input-group justify-content-center d-none" id="finalizar">
                                <a href="${pageContext.request.contextPath}/AreaVenta/SolicitarNIT.jsp" type="button" class="btn btn-success btn-lg">Procesar Compra</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../Extra/JS.jsp"/>
        <script src="${pageContext.request.contextPath}/JSExtra/RegistrarVenta.js"></script>

    </body>
</html>
