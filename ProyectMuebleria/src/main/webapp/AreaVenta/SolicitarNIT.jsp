<%-- 
    Document   : InicioVenta
    Created on : 26/08/2021, 10:28:12
    Author     : joel
--%>

<%@page import="ModeloVenta.ValidarSesionVenta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitar NIT</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSSExtra/SolicitarNIT.css"/>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <%--Se valida que sea un empleado venta y tenga sesion activa el que ingrese al formulario, sino regresar al inicio--%>
        
        <%ValidarSesionVenta.validarSesion(request, response);
            if (session.getAttribute("id_compras") == null) {
                response.sendRedirect(request.getContextPath() + "/RegistrarVenta");
            }%>

        <div class="container-fluid">
            <div class="row align-items-center justify-content-around" style="height: 1000px;">
                <div class="col-6">
                    <h3>Registrar Cliente</h3>
                    <a class="btn btn-dark" href="${pageContext.request.contextPath}/AreaVenta/RegistrarCliente.jsp">Registrar</a>
                </div>
                <div class="col-6">
                    <!<!-- Mensaje de Error -->
                    <c:if test="${error!=null}"> <p class="alert alert-danger" style="text-align: center">${error}</p></c:if>

                        <h3>Ingresar con NIT</h3>
                        <form action="${pageContext.request.contextPath}/ControladorNIT" method="POST">
                        <div class="input-group mb-3">
                            <div class="input-group mb-3 justify-content-center">
                                <div class="col-6 ">
                                    <input style="text-align: center" type="text" class="form-control" name="NIT_cliente" id="inputNIT" placeholder="Ingrese NIT">
                                </div>
                            </div>
                            <div class="input-group">
                                <div class="input-group justify-content-center">
                                    <button type="submit" class="btn btn-success">Agregar NIT</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
