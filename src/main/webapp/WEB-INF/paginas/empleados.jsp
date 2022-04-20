<%-- 
    Document   : empleados
    Created on : 11-03-2021, 17:31:14
    Author     : Carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_CL"></fmt:setLocale>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Se incluye el informacion de sesion -->
<%@page session="true" %>
<%
    HttpSession sesion_actual = request.getSession(true);    
    String usuario = (String) sesion_actual.getAttribute("Usuario");

    if (usuario == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Empleados</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Se importa Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- Se importan Iconos de Font Awesome -->
        <script src="https://kit.fontawesome.com/a03d9c12dd.js" crossorigin="anonymous"></script>  
    </head>
    <body>
        <!-- Se incluye el cabecero -->
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"></jsp:include>
        <!-- Se incluye barra de navegacion -->
        <jsp:include page="/WEB-INF/paginas/comunes/barraNavegacion.jsp"></jsp:include>
        <!-- Se importa Bootstrap JS -->   
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>    
        <!-- Se importa jQuery,Popper.js y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        
        <!-- Contenido de la pagina -->
        <div class="container-fluid">
            <!-- Tarjeta que contiene el Botón  Agregar Empleado -->
            <div class="card">
                <section id="actions" class="py-4 mb-4 bg-light">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-3">
                                <a class="btn btn-primary btn-block" href="EmpleadoController?menu=empleados&accion=AbrirPagina" ><i class="fas fa-plus"></i> Agregar Empleado</a> 
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <!-- Fin Botón  Agregar Empleado -->
            <div class="card">
                <!-- Card-header -->
                <div class="card-header text-center">
                    <h4>Listado de Empleados</h4> 
                    <c:if test="${not empty requestScope.mensaje}">
                        <div class="alert alert-danger" role="alert">
                            <button type="button" class="close" data-dismiss="alert"><i class="fa fa-window-close" aria-hidden="true"></i></button>
                            <strong>${requestScope.mensaje}</strong><!-- Mensaje de resultado de agregar o actualizar el registro --> 
                        </div>
                    </c:if>                    
                </div>
                <!-- Fin card-header -->   
                <!-- Card-body -->
                <div class="card-body">                    
                    <!-- Tabla del mantenedor de Empleados-->
                    <div class="card col-sm-12">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Nombre</th>                            
                                    <th>Teléfono</th>
                                    <th>Email</th>
                                    <th>Creación Registro</th>
                                    <th>Actualización Registro</th>
                                    <th>Username</th>
                                    <th>Tipo Usuario</th>
                                    <th>Unidad</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Se trae la lista de los empleados enviada por el controlador-->
                                <c:forEach var="empleado" items="${listaEmpleados}" varStatus="id">
                                    <tr>                            
                                        <td>${id.count}</td><!-- valor autoincrementable que reemplaza el id-->
                                        <td>${empleado.getNombre()} ${empleado.getApellidoPaterno()} ${empleado.getApellidoMaterno()}</td>                                
                                        <td>${empleado.getTelefono()}</td>
                                        <td>${empleado.getEmail()}</td>
                                        <td><fmt:formatDate value="${empleado.getFechaCreacionRegistro()}" type="date"/></td>
                                        <td><fmt:formatDate value="${empleado.getFechaModificacionRegistro()}" type="date"/></td>
                                        <td>${empleado.getUsername()}</td>
                                        <td>${empleado.getObjTipoUsuario().getTipoUsuario()}</td>
                                        <td>${empleado.getObjUnidad().getUnidad()}</td>
                                        <td>                                            
                                            <a class="btn btn-warning" href="EmpleadoController?menu=empleados&accion=Editar&id=${empleado.getIdEmpleado()}"><i class="fa fa-pencil"></i> </a> 
                                            <a class="btn btn-danger"  href="EmpleadoController?menu=empleados&accion=Eliminar&id=${empleado.getIdEmpleado()}"><i class="fas fa-trash"></i> </a>
                                            <a class="btn btn-success" href="EmpleadoController?menu=empleados&accion=SeleccionaParaEditarPassword&id=${empleado.getIdEmpleado()}" ><i class="fa fa-key"></i> </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- Fin Tabla del mantenedor de Empleados-->
                </div>
                <!-- Fin card-body -->
            </div>
            <!-- Fin card -->            
        </div>
	<!-- Fin container -->
        <!-- Se incluye pie de pagina -->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"></jsp:include> 
    </body>
</html>
