<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/AreaFinanciera/InicioFinanciera.jsp">Area Financiera y Administrativa</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Funciones Mueble
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/CreacionMueble">Creacion de Mueble</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/VentasDia">Editar Mueble</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Administracion Usuarios
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/CreacionUsuario">Creacion Usuario</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/RestringirUsuario">Cancelar Usuarios</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/RegistrarVenta?show=sale">Realizar Venta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/AreaVenta/Devolucion.jsp">Area de Devoluciones</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reportes Con Intervalo
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/AreaVenta/ComprasCliente.jsp">Consultas de compras de cliente</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/AreaVenta/DevolucionesCliente.jsp">Consultas de devoluciones de cliente</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reportes Sin Intervalo
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/RegistrarVenta?show=stock">Consulta de Muebles Disponibles en Sala de Venta</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/VentasDia">Consultas de ventas del dia</a></li>
                    </ul>
                </li>
            </ul>
            <span class="d-flex navbar-text text-white">
                <%="Nombre:" + request.getSession().getAttribute("nombre")%>
            </span>
            <div class="p-2">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/ControlLogOut">Cerrar Sesion</a>
            </div>
        </div>
    </div>
</nav>

