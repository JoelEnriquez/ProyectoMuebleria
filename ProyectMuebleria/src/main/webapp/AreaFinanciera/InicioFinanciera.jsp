<%-- 
    Document   : InicioFinanciera
    Created on : 26/08/2021, 02:14:51
    Author     : joel
--%>

<%@page import="ModeloFinanciera.ValidarSesionFinanciera"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio Financiera</title>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <% ValidarSesionFinanciera.validarSesion(request, response);%>
        <jsp:include page="/NavBars/NavBarFinanciera.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col align-self-center">
                    <img class="img-fluid mt-5 rounded img-thumbnail" src="${pageContext.request.contextPath}/resources/Financiera1.jpg" alt="250">
                </div>
            </div>
        </div>

        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
