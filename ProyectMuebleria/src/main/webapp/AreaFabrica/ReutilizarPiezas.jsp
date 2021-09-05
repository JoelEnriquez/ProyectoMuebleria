<%-- 
    Document   : Devolucion
    Created on : 4/09/2021, 01:46:48
    Author     : joel
--%>
<%@page import="ModeloFabrica.ValidarSesionFabrica"%>
<%@page import="ModeloVenta.ValidarSesionVenta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reutilizar Piezas</title>
        <jsp:include page="../Extra/CSS.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/RescatarPiezas.css"/>
    </head>
    <body>
        <%ValidarSesionFabrica.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFabrica.jsp"/>

        <div class="container">
            <c:if test="${success!=null}">
                <h6 class="display-6">Se ha realizado la devolucion de forma exitosa</h6>        
            </c:if>
            <c:if test="${error!=null}"> <h6 class="alert alert-danger">${error}</h6></c:if>
            <div class="row justify-content-around mt-5">
                <div class="col-12 align-self-center">
                    <div class="table-data">
                        <table class="table table-striped table-bordered mt-5">
                            <thead class="table-dark">
                                <tr>
                                    <th class="th-sm">Id de Ensamble</th>
                                    <th class="th-sm">Nombre Mueble</th>
                                    <th class="th-sm">Precio de Ensamble</th>
                                    <th class="th-sm">Rescatar piezas</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${info_devoluciones}" var="info_devolucion">
                                    <tr>
                                        <td>${info_devolucion.idEnsamble}</td>
                                        <td>${info_devolucion.nombreMueble}</td>
                                        <td>${info_devolucion.costoEnsamble}</td>
                                        <td>
                                            <a class="btn btn-warning"
                                               <%--Mandar id ensamble para obtener la receta del mueble --%>
                                                href="${pageContext.request.contextPath}/ReutilizarPiezas?nombre=${info_devolucion.nombreMueble}&id=${info_devolucion.idEnsamble}">
                                                Ejecutar
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Id de Ensamble</th>
                                    <th>Nombre Mueble</th>
                                    <th>Precio de Ensamble</th>
                                    <th>Rescatar piezas</th>
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
