<%-- 
    Document   : Devolucion
    Created on : 4/09/2021, 01:46:48
    Author     : joel
--%>
<%@page import="ModeloVenta.ValidarSesionVenta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Devolucion</title>
        <jsp:include page="../Extra/CSS.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/Devolucion.css"/>
    </head>
    <body>
        <%ValidarSesionVenta.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarVenta.jsp"/>

        <div class="container">
            <c:if test="${success!=null}">
                <h6 class="display-6">Se ha realizado la devolucion de forma exitosa</h6>        
            </c:if>
            <div class="row justify-content-around mt-5">
                <div class="col-6 align-self-first">
                    <c:if test="${error!=null}"> <p class="alert alert-danger">${error}</p></c:if>
                    <form action="${pageContext.request.contextPath}/Devolucion" method="POST">
                        <h3 class="display-6">Ingresar ID Factura</h3>
                        <div class="input-group mb-3 justify-content-center">
                            <div class="col-auto">
                                <input type="text" class="form-control" name="idFactura" id="inputFactura" placeholder="ID Factura" required>
                            </div>
                        </div>
                        <div class="input-group mb-3 mt-2 justify-content-center">
                            <div class="col-auto">
                                <button type="submit" class="btn btn-dark">Confirmar</button>
                            </div>
                        </div>   
                    </form>
                </div>
                <div class="col-6">
                    <h3 class="display-6">Factura</h3>
                    <form>
                        <fieldset disabled class="datos_factura">
                            <div class="form-group mb-3">
                                <label for="disabledId">ID Factura</label>
                                <input type="text" value="${factura.idFactura}" name="id" id="disabledId" class="form-control">
                            </div>
                            <div class="form-group mb-3">
                                <label for="disabledFecha">Fecha</label>
                                <input type="text" value="${factura.fechaCompra}" name="fecha" id="disabledFecha" class="form-control">
                            </div>
                            <div class="form-group mb-3">
                                <label for="disabledPrecio">Monto Total</label>
                                <input type="text" value="${factura.precioCompra}" name="precio" id="disabledPrecio" class="form-control">
                            </div>
                            <div class="form-group mb-3">
                                <label for="disabledNIT">NIT</label>
                                <input type="text" value="${factura.NITCliente}"  name="nit" id="disabledNIT" class="form-control">
                            </div>
                        </fieldset>
                    </form>
                    <div class="table-data">
                        <table class="table table-striped table-bordered mt-5">
                            <thead class="table-dark">
                                <tr>
                                    <th class="th-sm">Id de Ensamble</th>
                                    <th class="th-sm">Nombre Mueble</th>
                                    <th class="th-sm">Precio</th>
                                    <th class="th-sm">Devolucion</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${detalles}" var="detalle">
                                    <tr>
                                        <td>${detalle.idEnsamble}</td>
                                        <td>${detalle.nombreMueble}</td>
                                        <td>${detalle.costoMueble}</td>
                                        <td>
                                            <a class="btn btn-warning"
                                               <%--Mandar id ensamble para setear 0 su coste y devolucion en 1 --%>
                                                href="${pageContext.request.contextPath}/Devolucion?id=${detalle.idEnsamble}&idFactura=${factura.idFactura}">
                                                Ejecutar Devolucion
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id de Ensamble</th>
                                    <th>Nombre Mueble</th>
                                    <th>Precio</th>
                                    <th>Devolucion</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>

        </div>


        <jsp:include page="../Extra/JS.jsp"/>
    </body>
</html>
