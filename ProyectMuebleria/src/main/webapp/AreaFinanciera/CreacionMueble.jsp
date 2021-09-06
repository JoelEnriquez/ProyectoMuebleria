<%-- 
    Document   : StockPiezas
    Created on : 26/08/2021, 22:25:26
    Author     : joel
--%>

<%@page import="ModeloFinanciera.ValidarSesionFinanciera"%>
<%@page import="ModeloFabrica.ValidarSesionFabrica"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creacion de Usuario</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/CreacionMueble.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>   
    </head>
    <body>
        <% ValidarSesionFinanciera.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFinanciera.jsp"/>

        <div class="container">
            <c:if test="${error!=null}"> <p class="alert alert-warning">${error}</p></c:if>
            <c:if test="${success!=null}"> <p class="alert alert-success">${success}</p></c:if>
                <div class="row justify-content-around mt-5">
                    <div class="col-7">
                        <div class="row justify-content-center">
                            <h5 class="display-6">Lista de Muebles</h5>
                            <div class="table-data">
                                <table class="table table-striped table-bordered">
                                    <thead class="table-info">
                                        <tr>
                                            <th class="th-sm">#</th>
                                            <th class="th-sm">Nombre Mueble</th>
                                            <th class="th-sm">Precio Venta</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tablaFiltro">
                                    <c:forEach items="${muebles}" var="mueble">
                                        <tr>
                                            <td>${mueble.numeroMueble}</td>
                                            <td>${mueble.nombre}</td>
                                            <td>${mueble.precio}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th>#</th>
                                        <th>Nombre Mueble</th>
                                        <th>Precio Venta</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                    <div class="w-100 mt-2 mb-2"></div>
                    <div class="row justify-content-center ">
                        <h5 class="display-6">Lista de Recetas</h5>
                        <div class="table-data">
                            <table class="table table-striped table-bordered">
                                <thead class="table-info">
                                    <tr>
                                        <th class="th-sm">#</th>
                                        <th class="th-sm">Nombre de Mueble</th>
                                        <th class="th-sm">Tipo de Pieza</th>
                                        <th class="th-sm">Numero piezas</th>
                                    </tr>
                                </thead>
                                <tbody id="tablaFiltro">
                                    <c:forEach items="${recetas}" var="receta">
                                        <tr>
                                            <td>${receta.numeroEnsamble}</td>
                                            <td>${receta.nombreMueble}</td>
                                            <td>${receta.nombrePieza}</td>
                                            <td>${receta.cantidadPieza}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th>#</th>
                                        <th>Nombre de Mueble</th>
                                        <th>Tipo de Pieza</th>
                                        <th>Numero piezas</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-5">
                    <div class="row justify-content-center border border-dark">
                        <div class="col datos_mueble">
                            <h6 class="display-6">Crear Mueble</h6>
                            <form method="POST" action="${pageContext.request.contextPath}/CreacionMueble">
                                <input>
                                <button/>
                            </form>
                        </div>
                    </div>
                    <div class="row justify-content-center border border-dark">
                        <div class="col-5">
                            <h6 class="display-6">Asignar Receta</h6>
                        </div>
                        <div class="col-7">
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../Extra/JS.jsp"/>
    <script src="${pageContext.request.contextPath}/JSExtra/FuncionFiltro.js"></script>

</body>
</html>
