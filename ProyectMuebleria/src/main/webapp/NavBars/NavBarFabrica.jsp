<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/AreaFabrica/InicioFabrica.jsp"> Area de Fabrica</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/ConsultarInventario?op=inventario">Inventario</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Piezas
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/RegistrarPiezas">Registrar</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ConsultarInventario?op=modificar">Modificar</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/ControladorEnsambleMueble">Ensamblar y Muebles Creados</a>
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

