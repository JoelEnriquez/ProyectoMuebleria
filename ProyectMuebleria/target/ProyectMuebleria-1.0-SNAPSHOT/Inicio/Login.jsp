<%-- 
    Document   : Login
    Created on : 14/08/2021, 22:02:46
    Author     : joel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <jsp:include page="/Extra/CSS.jsp"/>
    </head>
    <body>
        
        <c:if test="${fail!=null}"><p class="alert alert-danger" style="text-align: center">${fail}</p></c:if>

        <div class="container">
            <div class="row justify-content-center mt-5" >
                <div class="card" style="width: 18rem;">
                    <img src="${pageContext.request.contextPath}/resources/Fabrica1.jpeg" class="card-img-top" alt="10">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/Login" method="POST">
                            <div class="form-group">
                                <label>Nombre</label>
                                <input type="text" class="form-control" name="nombre">
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
