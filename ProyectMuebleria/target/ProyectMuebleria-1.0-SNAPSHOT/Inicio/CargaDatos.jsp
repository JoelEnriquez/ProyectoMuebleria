<%-- 
    Document   : CargaDatos
    Created on : 14/08/2021, 22:03:03
    Author     : joel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ModelosInicio.VerificarDatos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cargar Datos</title>
        <link rel="stylesheet" href="CSSExtra/CSSCargaDatos.css"/>
        <jsp:include page="/Extra/CSS.jsp"/>

    </head>
    <body>
        <% if (request.getAttribute("Sucess") == null) {%>

        <%--Se verifican si ya hay un llenado en la base de datos, si hay, redirigir a login --%>
        <%
            VerificarDatos verificar = new VerificarDatos();
            int resultAux = verificar.comprobarDatos();
            if (resultAux == 1) {
                response.sendRedirect(request.getContextPath() + "/Inicio/Login.jsp");
            }
        %>


        <div class="container">
            <div class="row" style="text-align: center">
                <div class="col-12">
                    <header class="mt-5 mb-5">
                        <h1>Carga de Archivo</h1>
                    </header>
                    <div class="container">
                        <FORM class="col-12 caja2" METHOD="POST" ACTION="../CargaArchivo" enctype="multipart/form-data">  
                            <div class="col-12">
                                <input class="form-control" type="file"  name="archivoTXT" id="archivoTXT" placeholder="Elija el Archivo" required>
                            </div>
                            <div class="mt-5">
                                <input class="btn btn-primary" type="submit" value="Cargar">
                                <input class="btn btn-secundary" type="reset" value="Eliminar Datos">
                            </div>
                        </FORM>
                    </div>
                </div>
            </div>
        </div>

        <% } else {%>

        <div class="container" style="padding-top: 150px" >
            <h1 class="mb-5" style="text-align: center">
                Carga de Archivo
            </h1>
            <div class="fila-respuesta row justify-content-around">
                <div class="col col-5 align-self-center">
                    <% if ((boolean) request.getAttribute("Sucess")) {%>
                    <div class="alert alert-success" style="text-align: center">
                        Carga de Archivo Realizada con Exito
                    </div>
                    <div class="row justify-content-center">
                        <a href="${pageContext.request.contextPath}/Inicio/Login.jsp" class="btn btn-primary">
                            Iniciar Sesion
                        </a>
                    </div>

                    <% } else {%>
                    <div class="alert alert-danger " style="text-align: center">
                        Fallo en la Carga de Archivo
                    </div>
                    <div class="row justify-content-center">
                        <a href="index.jsp" class="btn btn-primary">
                            Volver a Intentar
                        </a>
                    </div>

                    <% }%>
                </div>
                <div class="col errores col-5" style="background: #f3f3f3;height: 600px">
                    <div style="height: 600px; width: 100%; overflow-y: scroll;">
                        <table class="table table-striped table-bordered mt-5" style="text-align: center">
                            <thead class="table-dark">
                                <tr>
                                    <th class="th-sm">Linea de Error</th>
                                    <th class="th-sm">Tipo de Error</th>
                                    <th class="th-sm">Mensaje de Error</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${errores}" var="error">
                                <tr>
                                    <td>${error.numeroLineaError}</td>
                                    <td>${error.tipoError}</td>
                                    <td>${error.mensajeError}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Linea de Error</th>
                                    <th>Tipo de Error</th>
                                    <th>Mensaje de Error</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <% }%> 

        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
