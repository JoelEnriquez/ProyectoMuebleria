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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/Usuarios.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>   
    </head>
    <body>
        <% ValidarSesionFinanciera.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFinanciera.jsp"/>

        <div class="container">
            <c:if test="${error!=null}"> <p class="alert alert-danger">${error}</p></c:if>
            <c:if test="${success!=null}"> <p class="alert alert-success">Se ha creado el usuario correctamente</p></c:if>
            <div class="row justify-content-around mt-5">
                <div class="col-8">
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
                                </tr>
                            </thead>
                            <tbody id="tablaFiltro">
                                <c:forEach items="${usuarios}" var="usuario">
                                    <tr>
                                        <td>${usuario.numeroUsuario}</td>
                                        <td>${usuario.nombre}</td>
                                        <td>${usuario.nombreTipoUsuario}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>#</th>
                                    <th>Nombre de Usuario</th>
                                    <th>Tipo de Usuario</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
                <div class="col-4 align-self-center">
                    <form action="${pageContext.request.contextPath}/CreacionUsuario" method="POST">
                        <h3 style=>Crear Usuario</h3>
                        <div class="input-group mb-3">
                            <!-- Lista de Tipos de Usuario Existentes -->
                            <label class="input-group-text" for="inputTipoUsuario">Tipo de Usuario</label>
                            <select class="form-select" aria-label=".form-select-lg example" id="inputTipoUsuario" name="tipo">
                                <option value="1">Area Fabrica</option>
                                <option value="2">Area Venta</option>
                                <option value="3">Area Financiera y Admin</option>
                            </select>
                        </div>
                        <div class="input-group mb-3 justify-content-between">
                            <label class="input-group-text" for="inputNombre">Nombre</label>
                            <div class="col">
                                <input type="text" class="form-control" name="nombre" id="inputNombre" placeholder="Nombre de Usuario" required>
                            </div>
                        </div>
                        <div class="input-group mb-3 justify-content-between">
                            <label class="input-group-text" for="inputPassword">Password</label>
                            <div class="col">
                                <input type="text" class="form-control" name="password" id="inputPassword" placeholder="Contraseña" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-12 offset-6">
                                <button type="submit" class="btn btn-outline-success">Crear</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
                        
        <jsp:include page="../Extra/JS.jsp"/>
        <script src="${pageContext.request.contextPath}/JSExtra/FuncionFiltro.js"></script>

    </body>
</html>
