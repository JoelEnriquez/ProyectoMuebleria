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
        <title>Registrar Piezas</title>
        <link rel="stylesheet" href="CSSExtra/RegistrarPiezas.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>   
    </head>
    <body>
        <% ValidarSesionFabrica.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFabrica.jsp"/>

        <div class="container">
            <c:if test="${error!=null}"> <p class="alert alert-danger" style="text-align: center">${error}</p></c:if>
            <c:if test="${success!=null}"> <p class="alert alert-success" style="text-align: center">Se ha realizado el registro correctamente</p></c:if>
            <div class="row justify-content-around mt-5">
                <div class="col-5">
                    <div class="table-data">
                        <table class="table table-striped table-bordered mt-5">
                            <thead class="table-dark">
                                <tr>
                                    <th class="th-sm">Tipo</th>
                                    <th class="th-sm">Cantidad de Stock</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${stock}" var="pieza">
                                    <tr>
                                        <td>${pieza.tipo}</td>
                                        <td <c:if test="${pieza.cantidadStock<10}">class="bg-danger text-white"</c:if>>
                                            ${pieza.cantidadStock}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Tipo</th>
                                    <th>Cantidad de Stock</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
                <div class="col-5 align-self-center">
                    <form action="${pageContext.request.contextPath}/RegistrarPiezas" method="POST">
                        <h3 style="text-align: center">Agregar Stock</h3>
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="inputNombrePieza">Tipo de Pieza</label>
                            <select class="form-select" style="text-align: center" aria-label=".form-select-lg example" id="inputNombrePieza" name="pieza">
                                <c:forEach items="${tipo_piezas}" var="nombre">   
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
                        <div class="input-group mb-3 justify-content-between">
                            <label class="input-group-text" for="inputPrecio">Precio</label>
                            <div class="col-sm-9">
                                <input style="text-align: center" type="number" min="0" class="form-control" name="precio" step="0.01" id="inputPrecio" placeholder="Precio">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-12 offset-6">
                                <button type="submit" class="btn btn-outline-info">Guardar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>





        </div>
        <jsp:include page="../Extra/JS.jsp"/>


    </body>
</html>
