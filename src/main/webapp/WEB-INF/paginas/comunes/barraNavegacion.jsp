<%-- 
    Document   : barraNavegacion
    Created on : 09-03-2021, 17:49:27
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
    HttpSession sesion_actual = request.getSession(true);
    String nombre = (String) sesion_actual.getAttribute("Nombre");
    String apPaterno = (String) sesion_actual.getAttribute("ApellidoPaterno");
    String apMaterno = (String) sesion_actual.getAttribute("ApellidoMaterno");
    String usuario = (String) sesion_actual.getAttribute("Usuario");
    String telefono = (String) sesion_actual.getAttribute("Telefono");
    String email = (String) sesion_actual.getAttribute("Email");
    String tipoUsuario = (String) sesion_actual.getAttribute("TipoUsuario");

    if (nombre == null && apPaterno == null && tipoUsuario == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ficha Técnica</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <div class="container-fluid">

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-dark" href="HomeController?menu=home" ><i class="fa fa-home"></i> Home</a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="FichaTecnicaController?menu=fichaTecnica&accion=Listar" >Ficha Técnica</a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="EmpleadoController?menu=empleados&accion=Listar" >Empleados</a>
                        </li> 
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="RegistroIPController?menu=registrosIP&accion=Listar" >Registro de IP</a>
                        </li>                                                 
                    </ul>
                    <!-- Dropdown Administración de Tipos -->
                    <div class="dropdown">
                        <button style="margin-left: 10px; border: none" class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            Administración de Tipos
                        </button>
                        <ul class="dropdown-menu text-left" aria-labelledby="dropdownMenuButton2">
                            <li><a class="dropdown-item" href="TipoUsuarioController?menu=tiposUsuario&accion=Listar" >Tipos de Usuario</a></li>
                            <li><a class="dropdown-item" href="UnidadController?menu=unidades&accion=Listar" >Unidades</a></li>  
                            <li><a class="dropdown-item" href="TipoEquipoController?menu=tiposEquipo&accion=Listar" >Tipos de Equipo</a></li>
                            <li><a class="dropdown-item" href="MarcaController?menu=marcas&accion=Listar" >Marcas</a></li>
                            <li><a class="dropdown-item" href="TipoProcesadorController?menu=tiposProcesador&accion=Listar" >Tipos de Procesador</a></li>
                            <li><a class="dropdown-item" href="TipoRAMController?menu=tiposRam&accion=Listar" >Tipos de Memoria RAM</a></li>
                            <li><a class="dropdown-item" href="TipoDiscoDuroController?menu=tiposDiscoDuro&accion=Listar" >Tipos de Disco Duro</a></li>
                            <li><a class="dropdown-item" href="TipoAdaptadorRedController?menu=tiposAdaptadorRed&accion=Listar" >Tipos de Adaptador de Red</a></li>
                            <li><a class="dropdown-item" href="EstadoOperacionalController?menu=estadosOperacionales&accion=Listar" >Tipos de Estados Operacionales</a></li>
                            <li><a class="dropdown-item" href="SistemaOperativoController?menu=sistemasOperativos&accion=Listar" >Sistemas Operativos</a></li>                            
                        </ul>
                    </div>
                    <!-- Fin dropdown -->
                    <!-- Dropdown cerrar sesion-->
                    <div class="dropdown">
                        <button style="margin-left: 10px; border: none" class="btn btn-outline-dark dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-user-circle" aria-hidden="true"></i> Bienvenid@ <%= nombre%>
                        </button>
                        <ul class="dropdown-menu text-left" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item text-center"><i class="fa fa-user-secret fa-5x"></i></a></li>
                            <li><a class="dropdown-item">Nombre: <%= nombre%> <%= apPaterno%> <%= apMaterno%> </a></li>
                            <li><a class="dropdown-item">Usuario: <%= usuario%> </a></li>
                            <li><a class="dropdown-item">Teléfono: <%= telefono%> </a></li>
                            <li><a class="dropdown-item">Email: <%= email%> </a></li>
                            <li><a class="dropdown-item">Tipo de Usuario: <%= tipoUsuario%> </a></li>
                            <div class="dropdown-divider"> </div> 
                            <li><a class="dropdown-item text-center" href="LoginController?accion=Salir"><i class="fa fa-sign-out" aria-hidden="true"></i> Salir</a></li>
                        </ul>                            
                    </div>
                    <!-- Fin dropdown-->
                </div>
            </div>
        </nav>
    </body>
</html>
