<%-- 
    Document   : CargaDatos
    Created on : 14/08/2021, 22:03:03
    Author     : joel
--%>

<%@page import="ModelosInicio.VerificarDatos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cargar Datos</title>
        <link rel="stylesheet" href="../CSSExtra/CSSCargaDatos.css" />
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
            <div class="row">
                <div class="col-12">
                    <header class="mt-5">
                        <h1>Carga de Archivo</h1>
                    </header>
                    <div class="container " style="padding-top: 150px" >
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
                <div class="col-5">
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
                <div class="col-5" style="background: #f2f2f2">
                    hi
                </div>
            </div>
        </div>
        <% }%> 

        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
