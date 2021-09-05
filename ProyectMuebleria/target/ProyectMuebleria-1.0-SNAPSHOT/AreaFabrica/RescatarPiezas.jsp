<%-- 
    Document   : RescatarPiezas
    Created on : 4/09/2021, 13:02:44
    Author     : joel
--%>

<%@page import="ModeloFabrica.ValidarSesionFabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rescatar Piezas</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/RescatarPiezas.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <%ValidarSesionFabrica.validarSesion(request, response);%>
        <c:if test="${success!=null}">
            <jsp:include page="/NavBars/NavBarFabrica.jsp"/>
            <h6 class="mt-5 display-6">Se han rescatado las piezas correctamente</h6>        
        </c:if>


        <div class="container">
            <c:if test="${error!=null}"><h5 class="display-6 alert alert-danger">${error}</h5></c:if>
                <div class="row align-items-center justify-content-around" style="height: 1000px;">
                    <div class="col-6">
                    <c:if test="${finish==null}">
                        <form action="${pageContext.request.contextPath}/ReutilizarPiezas" method="POST">
                            <h3 class="display-6">Rescatar Piezas</h3>
                            <div class="d-none">
                                <label class="input-group-text" for="inputNombreMueble">Tipo de Mueble</label>
                                <select class="form-select" style="text-align: center" aria-label=".form-select-lg example" id="inputNombreMueble" name="mueble">
                                    <c:forEach items="${recetas}" var="receta">   
                                        <option value="${receta.nombreMueble}">${receta.nombreMueble}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="inputNombrePieza">Tipo de Pieza</label>
                                <select class="form-select" style="text-align: center" aria-label=".form-select-lg example" id="inputNombrePieza" name="pieza">
                                    <c:forEach items="${recetas}" var="receta">   
                                        <option value="${receta.nombrePieza}">${receta.nombrePieza}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-group mb-3 justify-content-between">
                                <label class="input-group-text" for="inputCantidad">Cantidad</label>
                                <div class="col-sm-9">
                                    <input style="text-align: center" type="number" min="0" class="form-control" name="cantidad" id="inputCantidad" placeholder="Cantidad">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-12 offset-6">
                                    <button type="submit" class="btn btn-outline-success">Rescatar</button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${finish!=null}"><a class="btn btn-success btn-lg offset-5" href="${pageContext.request.contextPath}/ReutilizarPiezas?nombre=finish">Finalizar</a></c:if>
                </div>
                <div class="col-6 align-self-center">
                    <h3 class="display-4">Receta</h3>
                    <div class="table-data">
                        <table class="table table-striped table-bordered mt-5">
                            <thead class="table-dark">
                                <tr>
                                    <th class="th-sm">Nombre Mueble</th>
                                    <th class="th-sm">Tipo de Pieza</th>
                                    <th class="th-sm">Cantidad</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${recetas}" var="receta">
                                    <tr>
                                        <td>${receta.nombreMueble}</td>
                                        <td>${receta.nombrePieza}</td>
                                        <td>${receta.cantidadPieza}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Nombre Mueble</th>
                                    <th>Tipo de Pieza</th>
                                    <th>Cantidad</th>
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
