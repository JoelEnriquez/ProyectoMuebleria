<%-- 
    Document   : Login
    Created on : 14/08/2021, 22:02:46
    Author     : joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <jsp:include page="/Extra/CSS.jsp"/>
    </head>
    <body>

        <%if (request.getAttribute("fail") != null) {%>
        <%if ((boolean) request.getAttribute("fail")) {%>
        <p class="alert alert-danger">Usuario o Contraseña Incorrecto</p>   
        <%}%>
        <%}%>

        <div class="container">
            <div class="row justify-content-center">
                <div class="card" style="width: 18rem;">
                    <img src="${pageContext.request.contextPath}/resources/doctor_and_patient.jpg" class="card-img-top" alt="10">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/Login" method="POST">
                            <div class="form-group">
                                <label>Codigo</label>
                                <input type="text" class="form-control" name="codigo">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" class="form-control" name="password">
                            </div>
                            <button type="submit" class="btn btn-primary mt-5">Ingresar</button>                      
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/Extra/JS.jsp"/>
    </body>
</html>
