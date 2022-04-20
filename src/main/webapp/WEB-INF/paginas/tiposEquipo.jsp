<%-- 
    Document   : marcas
    Created on : 11-03-2021, 21:11:37
    Author     : Carlos
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Tipos de Equipo</title>
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
            <div class="card">
                <!-- Card-header -->
                <div class="card-header text-center">
                    <h4>Tipos de Equipo</h4> 
                <c:if test="${not empty requestScope.mensaje}">
                    <div class="alert alert-danger" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><i class="fa fa-window-close" aria-hidden="true"></i></button>
                        <strong>${requestScope.mensaje}</strong><!-- Mensaje de resultado de agregar o actualizar el registro --> 
                    </div>
                </c:if>
                </div>
                <!-- Fin card-header -->
                <!-- Card-body -->
                <div class="card-body d-flex">
                    <!-- Formulario para agregar y/o editar un registro -->
                    <div class="card col-sm-3">
                        <br>
                        <label class="text-center"><strong>AGREGAR O ACTUALIZAR REGISTRO</strong></label>
                        <br>
                        <form action="TipoEquipoController?menu=tiposEquipo" method="POST">
                            <div class="form-group">
                                <label>Tipo de Equipo: </label>
                                <input type="text" name="txtTipoEquipo" class="form-control" value="${tipoEquipo.getTipoEquipo()}" required="true" placeholder="Ingrese Tipo de Equipo" autofocus="true">
                            </div> 
                            <div class="form-group text-center">
                                <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                <input type="reset" value="Limpiar" class="btn btn-secondary">
                                <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                            </div> 
                        </form>                        
                    </div>
                    <!-- Fin Formulario para agregar y/o editar un registro -->
                    
                    <!-- Tabla del mantenedor de Tipos de Equipo-->
                    <div class="card col-sm-9">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Estado Operativo</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Se trae la lista de los tipos de equipos enviada por el controlador-->
                                <c:forEach var="tipoEquipo" items="${listaTiposEquipo}" varStatus="id">
                                    <tr>                            
                                        <td>${id.count}</td><!-- valor autoincrementable que reemplaza el id-->
                                        <td>${tipoEquipo.getTipoEquipo()}</td>
                                        <td>
                                            <a class="btn btn-warning" href="TipoEquipoController?menu=tiposEquipo&accion=Editar&id=${tipoEquipo.getIdTipoEquipo()}"><i class="fa fa-pencil"></i> Editar</a> 
                                            <a class="btn btn-danger" href="TipoEquipoController?menu=tiposEquipo&accion=Eliminar&id=${tipoEquipo.getIdTipoEquipo()}"><i class="fas fa-trash"></i> Eliminar</a> 
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- Fin Tabla del mantenedor de Tipos de Equipo-->
                </div>
                <!-- Fin card-body -->
            </div>
            <!-- Fin card -->            
        </div><!-- Fin container -->
        <!-- Se incluye pie de pagina -->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"></jsp:include>         
    </body>
</html>
