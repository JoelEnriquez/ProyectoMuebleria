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
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reportes de Usuario
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/UsuarioMayoresVentas">Usuario que registra mas ventas</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/UsuarioMayoresGanancias">Usuario que registra mas ganancias</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/AreaFinanciera/ReporteMuebles.jsp">Reportes de Mueble</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reportes de Finanzas
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ReporteVentasFinanciera">Reporte de Ventas</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ReporteDevolucionesFinanciera">Reporte de Devoluciones</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ReporteGanancias">Reporte de Ganancias</a></li>
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

