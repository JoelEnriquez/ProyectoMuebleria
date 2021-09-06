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

        <div class="container-fluid">
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
                <div class="col-4">
                    <div class="row justify-content-center border border-dark mb-5 p-3">
                        <div class="col datos_mueble">
                            <h6 class="display-6">Crear Mueble</h6>
                            <form method="POST" action="${pageContext.request.contextPath}/CreacionMueble">
                                <div class="input-group mb-3 justify-content-between">
                                    <label class="input-group-text" for="inputNombre">Nombre</label>
                                    <div class="col">
                                        <input style="text-align: center" value="${mueble.nombre}" type="text" class="form-control" name="nombre" id="inputNombre" placeholder="Nombre de Mueble">
                                    </div>
                                </div>
                                <div class="input-group mb-3 justify-content-between">
                                    <label class="input-group-text" for="inputPrecio">Precio</label>
                                    <div class="col">
                                        <input style="text-align: center" value="${mueble.precio}" type="number" min="0" step="0.01" class="form-control" name="precio" id="inputPrecio" placeholder="Precio">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col offset-5">
                                        <button type="submit" class="btn btn-outline-info">Guardar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row justify-content-center border border-dark mb-5 p-3">
                        <div class="col-5">
                            <h6 class="display-6">Asignar Receta</h6>
                            <fieldset>
                                <form method="post" action="${pa}">
                                    <div class="input-group mb-3">
                                        <label class="input-group-text" for="inputNombrePieza">Tipo de Pieza</label>
                                        <select class="form-select" style="text-align: center" aria-label=".form-select-lg example" id="inputNombrePieza" name="pieza">
                                            <option value="${pieza.tipoPieza}" selected>${pieza.tipoPieza}</option>
                                            <c:forEach items="${tipo_piezas}" var="nombre">   
                                                <option value="${nombre}">${nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="input-group mb-3 justify-content-between">
                                        <label class="input-group-text" for="inputPrecio">Precio</label>
                                        <div class="col">
                                            <input style="text-align: center" value="${pieza.precio}" type="number" min="0" class="form-control" name="cantidad" id="inputPrecio" placeholder="Cantidad">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-12 offset-3">
                                            <button type="submit" class="btn btn-outline-info">Agregar</button>
                                        </div>
                                    </div>
                                </form>
                            </fieldset>
                        </div>
                        <div class="col-7">
                            <div class="table-data">
                                <table class="table table-striped table-bordered">
                                    <thead class="table-dark">
                                        <tr>
                                            <th class="th-sm">Tipo de Pieza</th>
                                            <th class="th-sm">Numero piezas</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tablaFiltro">
                                        <c:forEach items="${receta_actual}" var="receta">
                                            <tr>
                                                <td>${receta.nombrePieza}</td>
                                                <td>${receta.cantidadPieza}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Tipo de Pieza</th>
                                            <th>Numero piezas</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <a href="${pageContext.request.getContextPath}/CreacionMueble?crear=true" class="btn btn-success">Registrar Mueble</a>
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
