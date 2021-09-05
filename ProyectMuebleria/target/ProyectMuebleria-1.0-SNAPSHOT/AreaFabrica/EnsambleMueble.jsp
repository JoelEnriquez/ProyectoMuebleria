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
        <title>Ensamble de Muebles</title>
        <link rel="stylesheet" href="CSSExtra/RegistrarPiezas.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>   
    </head>
    <body>
        <% ValidarSesionFabrica.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFabrica.jsp"/>

        <div class="container">
            <c:if test="${error!=null}"> <p class="alert alert-danger" style="text-align: center">${error}</p></c:if>
            <c:if test="${success!=null}"> <p class="alert alert-success" style="text-align: center">Se ha realizado el/los ensamble/s correctamente</p></c:if>
                <div class="row justify-content-around mt-5">
                    <div class="col-5">
                        <div class="table-data">
                            <table class="table table-striped table-bordered mt-5">
                                <thead class="table-dark">
                                    <tr>
                                        <th class="th-sm">Id</th>
                                        <th class="th-sm">Fecha de Ensamble</th>
                                        <th class="th-sm">Precio de Ensamble</th>
                                        <th class="th-sm">Nombre de Usuario</th>
                                        <th class="th-sm">Nombre de Mueble</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${ensambles}" var="ensamble">
                                    <tr>
                                        <td>${ensamble.id}</td>
                                        <td>${ensamble.fechaEnsamble}</td>
                                        <td>${ensamble.precioEnsamble}</td>
                                        <td>${ensamble.nombreUsuario}</td>
                                        <td>${ensamble.nombreMueble}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Fecha de Ensamble</th>
                                    <th>Precio de Ensamble</th>
                                    <th>Nombre de Usuario</th>
                                    <th>Nombre de Mueble</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
                <div class="col-5 align-self-center">
                    <div class="row mb-5" style="background: #f2f2f2">
                        <form action="${pageContext.request.contextPath}/ControladorEnsambleMueble" method="GET">
                            <h3 style="text-align: center">Ordenar por Fecha de Ensamble</h3>            
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="mueble_creacion" id="mayor_menor" value="DESC" checked>
                                <label class="form-check-label" for="mayor_menor">
                                    Mayor a menor
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="mueble_creacion" id="menor_mayor" value="ASC">
                                <label class="form-check-label" for="menor_mayor">
                                    Menor a mayor
                                </label>
                            </div>
                            <div class="form-check form-check-inline offset-2"></div>
                            <button type="submit" class="btn btn-success">Ordenar</button>

                        </form>
                    </div>
                    <div class="row">
                        <form action="${pageContext.request.contextPath}/ControladorEnsambleMueble" method="POST">
                            <h3 style="text-align: center">Fabricar Mueble</h3>
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="inputNombreMueble">Nombre de Mueble</label>
                                <select class="form-select" style="text-align: center" aria-label=".form-select-lg example" id="inputNombreMueble" name="mueble">
                                    <c:forEach items="${nombre_muebles}" var="nombre">   
                                        <option value="${nombre}">${nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-group mb-3 justify-content-between">
                                <label class="input-group-text" for="inputCantidad">Cantidad</label>
                                <div class="col-sm-9">
                                    <input style="text-align: center" type="number" min="1" class="form-control" name="cantidad" id="inputCantidad" placeholder="Cantidad">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-12 offset-6">
                                    <button type="submit" class="btn btn-outline-info">Fabricar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../Extra/JS.jsp"/>


    </body>
</html>
