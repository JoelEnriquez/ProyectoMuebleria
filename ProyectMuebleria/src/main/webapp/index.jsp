<%-- 
    Document   : index
    Created on : 15/08/2021, 15:36:25
    Author     : joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comprobar Datos</title>
    </head>
    <body>
        <% if (request.getAttribute("SolicitudDatos") == null) {%>
        <% RequestDispatcher rd = request.getRequestDispatcher("ComprobarDatos");
            rd.forward(request, response);%>
        <% } else {%>
        <% if ((Integer)request.getAttribute("SolicitudDatos")==0) {%>
        <% response.sendRedirect("Inicio/CargaDatos.jsp"); %>
        <% } else {%>
        <% response.sendRedirect("Inicio/Login.jsp"); %>
        <% }%>
        <% }%>
    </body>
</html>
