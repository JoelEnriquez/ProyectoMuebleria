
<%@page import="ModeloFabrica.ValidarSesionFabrica"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Piezas</title>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <% ValidarSesionFabrica.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFabrica.jsp"/>

        <div class="container">
            <div class="row align-items-center">
                <div style="height: 800px; width: 100%; overflow-y: scroll;">
                    <table class="table table-striped table-bordered mt-5" style="text-align: center">
                        <thead class="table-dark">
                            <tr>
                                <th class="th-sm">Id</th>
                                <th class="th-sm">Nombre Pieza</th>
                                <th class="th-sm">Precio</th>
                                <th class="th-sm">Stock</th>
                                <th class="th-sm">Accion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${stockPiezas}" var="stockPieza">
                                <tr>
                                    <td>${stockPieza.id}</td>
                                    <td>${stockPieza.tipoPieza}</td>
                                    <td>${stockPieza.precio}</td>
                                    <td <c:if test="${stockPieza.cantidadStock<10}">class="bg-danger text-white"</c:if>>
                                        ${stockPieza.cantidadStock}
                                    </td>
                                    <td><a href="${pageContext.request.contextPath}/ModificarPiezas?edit=modify&id=${stockPieza.id}" 
                                           class="btn btn-lg btn-block btn-outline-warning">
                                            Editar
                                        </a>
                                        <a href="${pageContext.request.contextPath}/ModificarPiezas?edit=delete&id=${stockPieza.id}" 
                                           class="btn btn-lg btn-block btn-outline-danger">
                                            Eliminar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Id</th>
                                <th>Nombre Pieza</th>
                                <th>Precio</th>
                                <th>Stock</th>
                                <th>Accion</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>

        <jsp:include page="../Extra/JS.jsp"/>
    </body>
</html>
