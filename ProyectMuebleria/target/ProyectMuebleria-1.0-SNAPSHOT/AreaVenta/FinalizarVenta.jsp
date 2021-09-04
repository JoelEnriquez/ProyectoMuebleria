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
        <title>Finalizar Venta</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/FinalizarVenta.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>   
    </head>
    <body>
        <%--Se valida que sea un empleado venta y tenga sesion activa el que ingrese al formulario, sino regresar al inicio--%>
        <%
            ValidarSesionVenta.validarSesion(request, response);
            if (session.getAttribute("id_compras") == null || request.getAttribute("cliente") == null) {
               response.sendRedirect(request.getContextPath() + "/RegistrarVenta");
            }
        %>

        <c:if test="${success!=null}">
            <jsp:include page="/NavBars/NavBarVenta.jsp"/>
            <h3 style="text-align: center; background-color: mediumseagreen" class="mt-5 display-6">Se ha realizado la compra correctamente</h3>        
        </c:if>

        <c:if test="${success==null}">
            <div class="container">
                <c:if test="${error!=null}"> <p class="alert alert-danger" style="text-align: center">${error}</p></c:if>

                    <div class="row justify-content-around mt-5">
                        <div class="col-5">
                            <h3 class="mb-5 display-6">Datos de Cliente</h3>
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
                    <div class="div-registro col-5 align-self-center p-3" style="background: #777a95">
                        <h3>Compra Generada</h3>
                        <div class="row">
                            <table class="table-factura table table-success table-bordered mt-2">
                                <thead class="table-dark">
                                    <tr>
                                        <th class="th-sm">Id</th>
                                        <th class="th-sm">Nombre Mueble</th>
                                        <th class="th-sm">Precio</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${carrito}" var="ensamble">
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
                <div class=" resumen row mt-5  justify-content-center">
                    <div class="col align-self-center">
                        <h3 class="display-6">Coste total</h3>
                        <p class="lead" style="text-align: center">
                            El total de la compra es de: Q${costo}
                        </p>
                    </div>
                    <div class="col align-self-center">
                        <a class="btn btn-primary btn-lg offset-4" href="${pageContext.request.contextPath}/RealizarVenta?costo=${costo}&NIT=${cliente.NIT}">
                            Finalizar Compra
                        </a>
                    </div>
                </div>
            </div>
        </c:if>
        <jsp:include page="../Extra/JS.jsp"/>
        <script src="${pageContext.request.contextPath}/JSExtra/RegistrarVenta.js"></script>

    </body>
</html>
