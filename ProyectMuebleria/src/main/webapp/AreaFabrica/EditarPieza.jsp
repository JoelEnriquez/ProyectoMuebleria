<%-- 
    Document   : EditarPieza
    Created on : 29/08/2021, 22:21:35
    Author     : joel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Pieza</title>
        <jsp:include page="../Extra/CSS.jsp"/>
    </head>
    <body>
        <jsp:include page="/NavBars/NavBarFabrica.jsp"/>

        <div class="container">
            <div class="row align-items-center mt-5" style="height: 600px">
                <div class="col-12">
                    <c:if test="${success!=null}"> <p class="alert alert-success" style="text-align: center"> Cambio Generado Exitosamente </p></c:if>  
                    <c:if test="${error_change!=null}"> <p class="alert alert-danger mb-5" style="text-align: center">${error}</p></c:if>
                    <form action="${pageContext.request.contextPath}/ModificarPiezas" method="POST">
                        <input type="hidden" id="id" name="id" value="${pieza.id}">
                        <h3 style="text-align: center">Pieza con id#${pieza.id}</h3>
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="inputNombrePieza">Tipo de Pieza</label>
                            <select class="form-select" style="text-align: center" aria-label=".form-select-lg example" id="inputNombrePieza" name="pieza">
                                <option value="${pieza.tipoPieza}" selected>${pieza.tipoPieza}</option>
                                <c:forEach items="${tipo_piezas}" var="nombre">   
                                <option value="${nombre}">${nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-3 justify-content-between">
                            <label class="input-group-text" for="inputPrecio">Precio</label>
                            <div class="col-sm-11">
                                <input style="text-align: center" value="${pieza.precio}" type="number" class="form-control" name="precio" id="inputPrecio" placeholder="Precio">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-12 offset-6">
                                <button type="submit" class="btn btn-outline-info">Guardar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../Extra/JS.jsp"/>
    </body>
</html>
