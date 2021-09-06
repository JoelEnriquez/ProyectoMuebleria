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
        <title>Restringir Usuario</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/Usuarios.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>   
    </head>
    <body>
        <% ValidarSesionFinanciera.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFinanciera.jsp"/>

        <div class="container">
            <c:if test="${success!=null}"> <p class="alert alert-warning">Se ha restringido al usuario: ${success}</p></c:if>
            <div class="row justify-content-around mt-5">
                <div class="col-12">
                    <div class="row justify-content-first">
                        <div class="col-5 text-center">
                            <label for="inputFiltro">Busqueda por Filtro:</label>
                        </div>
                        <div class="col-5">
                            <input class="form-control" id="inputFiltro" type="text" placeholder="Filtro">
                        </div>
                    </div>
                    <div class="table-data">
                        <table class="table table-striped table-bordered mt-5">
                            <thead class="table-dark">
                                <tr>
                                    <th class="th-sm">#</th>
                                    <th class="th-sm">Nombre de Usuario</th>
                                    <th class="th-sm">Tipo de Usuario</th>
                                    <th class="th-sm">Revocar Acceso</th>
                                </tr>
                            </thead>
                            <tbody id="tablaFiltro">
                                <c:forEach items="${usuarios}" var="usuario">
                                    <tr>
                                        <td>${usuario.numeroUsuario}</td>
                                        <td>${usuario.nombre}</td>
                                        <td>${usuario.nombreTipoUsuario}</td>
                                        <td>
                                            <a class="btn btn-outline-danger" 
                                               href="${pageContext.request.contextPath}/RestringirUsuario?nombre=${usuario.nombre}">
                                                Restringir
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>#</th>
                                    <th>Nombre de Usuario</th>
                                    <th>Tipo de Usuario</th>
                                    <th>Revocar Acceso</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
                        
        <jsp:include page="../Extra/JS.jsp"/>
        <script src="${pageContext.request.contextPath}/JSExtra/FuncionFiltro.js"></script>

    </body>
</html>
