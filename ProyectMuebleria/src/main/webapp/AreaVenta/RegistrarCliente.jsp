<%-- 
    Document   : RegistrarCliente
    Created on : 2/09/2021, 19:54:55
    Author     : joel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Cliente</title>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>        
        <%--Se valida que sea un empleado venta y tenga sesion activa el que ingrese al formulario, sino regresar al inicio--%>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            if (session.getAttribute("nombre") == null || !session.getAttribute("persona").equals("Venta")) {
                response.sendRedirect(request.getContextPath() + "/ControlLogOut");
            } 
            else if (session.getAttribute("id_compras") == null) {
                response.sendRedirect(request.getContextPath() + "/ControlLogOut");
            }
        %>

        <div class="container">
            <div class="row align-items-center mt-5" style="height: 600px">
                <div class="col-12">
                    
                    <c:if test="${error!=null}"> <h5 class="alert alert-danger mb-5" style="text-align: center">${error}</h5></c:if>
                    
                    <form action="${pageContext.request.contextPath}/RegistrarCliente" method="POST">

                        <h3 style="text-align: center" class="mb-5 display-6">Registrar Info Cliente</h3>
                        <div class="input-group mb-3 justify-content-between">
                            <label class="input-group-text" for="inputNIT">NIT</label>
                            <div class="col-10">
                                <input style="text-align: center" value="${pieza.precio}" type="text" class="form-control" name="NIT" id="inputNIT" placeholder="Obligatorio" required>
                            </div>
                        </div>
                        <div class="input-group mb-3 justify-content-between">
                            <label class="input-group-text" for="inputNombre">Nombre</label>
                            <div class="col-10">
                                <input style="text-align: center" value="${pieza.precio}" type="text" class="form-control" name="nombre" id="inputNombre" placeholder="Obligatorio" required>
                            </div>
                        </div>
                        <div class="input-group mb-3 justify-content-between">
                            <label class="input-group-text" for="inputDireccion">Direccion</label>
                            <div class="col-10">
                                <input style="text-align: center" value="${pieza.precio}" type="text" class="form-control" name="direccion" id="inputDireccion" placeholder="Obligatorio" required>
                            </div>
                        </div>
                        <div class="input-group mb-3 justify-content-between">
                            <label class="input-group-text" for="inputMunicipio">Municipio</label>
                            <div class="col-10">
                                <input style="text-align: center" value="${pieza.precio}" type="text" class="form-control" name="municipio" id="inputMunicipio" placeholder="Opcional">
                            </div>
                        </div>
                        <div class="input-group mb-3 justify-content-between">
                            <label class="input-group-text" for="inputDepartamento">Departamento</label>
                            <div class="col-10">
                                <input style="text-align: center" value="${pieza.precio}" type="text" class="form-control" name="departamento" id="inputDepartamento" placeholder="Opcional">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-12 offset-6 mt-2">
                                <button type="submit" class="btn btn-success btn-lg">Guardar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
