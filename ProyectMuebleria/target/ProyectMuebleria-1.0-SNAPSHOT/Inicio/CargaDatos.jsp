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
        <jsp:include page="/Extra/CSS.jsp"/>
    </head>
    <body>
        <% if (request.getAttribute("Sucess") == null) {%>
        
        <%--Se verifican si ya hay un llenado en la base de datos, si hay, redirigir a login --%>
        <%  
            VerificarDatos verificar = new VerificarDatos();
            int resultAux = verificar.ComprobarDatos();
            if (resultAux != 0) {
                response.sendRedirect(request.getContextPath()+"/Inicio/Login.jsp");
            }
        %>
        
        <div class="container" style="padding-top: 150px" >
                <h1 class="align-content-lg-center">Carga de Archivo</h1>

                <FORM class="col-12 caja2" METHOD="POST" ACTION="../CargaArchivo" enctype="multipart/form-data">  
                    <div class="row mb-5 mt-2">
                        <div class="form-group col-md-4">
                            <label>Elija el Archivo TXT</label>
                        </div>
                        <div class="form-group col-md-4">

                            <input class="form-control" type="file"  name="archivoTXT" id="archivoTXT" placeholder="Elija el Archivo" accept=".txt" required>
                        </div>
                    </div>
                    
                    <input class="btn btn-primary" type="submit" value="Cargar">
                    <input class="btn btn-secundary" type="reset" value="Eliminar Datos">

                </FORM>
        </div>
        <% } else {%>
        <div class="container " style="padding-top: 150px" >
                <h1 class="align-content-lg-center">Carga de Archivo XML</h1>
            <% if ((boolean) request.getAttribute("Sucess")) {%>
            <div class="alert alert-success">
                Carga de Archivo Realizada con Exito
            </div>
                <a href="${pageContext.request.contextPath}/Inicio/Login.jsp" class="btn btn-primary">Iniciar Sesion</a>

            <% } else {%>
            <div class="alert alert-danger">
                Fallo en la Carga de Archivo
            </div>
                <a href="index.jsp" class="btn btn-primary">Volver a Intentar</a>
            <% }%>
        </div>
        <% }%> 
        
        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
