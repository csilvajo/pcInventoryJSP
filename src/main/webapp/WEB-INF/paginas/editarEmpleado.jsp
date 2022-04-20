<%-- 
    Document   : prueba
    Created on : 24-03-2021, 21:58:58
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
        <title>Editar Empleado</title>  
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
    <body class="text-left">
        <div class="container mt-4 col-lg-4">
            <div class="card">
                <div class="card-header text-center">
                    <span><h4><i class="fa fa-pencil-square" aria-hidden="true"></i> Editar Empleado</h4></span>
                </div>
                <div class="card-body">
                    <!-- Formulario Agregar Empleado -->
                    <form action="EmpleadoController?menu=empleados" method="POST">
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" name="txtNombre" class="form-control" value="${empleado.getNombre()}" minlength="4" required="true" placeholder="Nombre" >
                        </div> 
                        <div class="form-group">
                            <label>Apellido Paterno:</label>
                            <input type="text" name="txtApellidoPaterno" class="form-control" value="${empleado.getApellidoPaterno()}" minlength="4" required="true" placeholder="Apellido paterno">
                        </div>
                        <div class="form-group">
                            <label>Apellido Materno:</label>
                            <input type="text" name="txtApellidoMaterno" class="form-control" value="${empleado.getApellidoMaterno()}" minlength="4" required="true" placeholder="Apellido materno">
                        </div>
                        <div class="form-group">
                            <label>Teléfono:</label>
                            <input type="tel" name="txtTelefono" class="form-control" value="${empleado.getTelefono()}" pattern="[+][0-9]{2}[-][0-9]{1}[-][0-9]{8}" title="Ejemplo : +56-2-55354704" required="true" placeholder="+56-2-55354704">
                        </div>
                        <div class="form-group">
                            <label>Email:</label>
                            <input type="email" name="txtEmail" class="form-control" value="${empleado.getEmail()}" pattern="[a-zA-Z0-9_]*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"required="true" placeholder="correo@mail.com">
                        </div>
                        <div class="form-group">
                            <label>Username:</label>
                            <input type="text" name="txtUsername" class="form-control" value="${empleado.getUsername()}" minlength="4" required="true" placeholder="Nombre de usuario">
                        </div>                        
                        <div class="form-group">
                            <label>Tipo de Usuario: </label>
                            <select class="form-control" id="cboTipoUsuario" name="cboTipoUsuario" required="true">
                                <option selected disabled value="">Seleccione</option>
                                <c:forEach items="${listaTiposUsuario}" var="tipoUsuario">
                                    <c:choose>
                                        <c:when test="${empleado.getObjTipoUsuario().getIdTipoUsuario() == tipoUsuario.getIdTipoUsuario()}">
                                            <option value="${tipoUsuario.getIdTipoUsuario()}" selected >${tipoUsuario.getTipoUsuario()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${tipoUsuario.getIdTipoUsuario()}">${tipoUsuario.getTipoUsuario()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Unidad: </label>
                            <select class="form-control" id="cboUnidad" name="cboUnidad" required="true">
                                <option selected disabled value="">Seleccione</option>
                                    <c:forEach items="${listaUnidades}" var="unidad">
                                        <c:choose>
                                            <c:when test="${empleado.getObjUnidad().getIdUnidad() == unidad.getIdUnidad()}">
                                                <option value="${unidad.getIdUnidad()}" selected >${unidad.getUnidad()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${unidad.getIdUnidad()}">${unidad.getUnidad()}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </select>                             
                        </div>
                        <div class="form-group text-center">
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success">                            
                            <a class="btn btn-warning" href="EmpleadoController?menu=empleados&accion=Listar"><i class="fa fa-undo" aria-hidden="true"></i> Volver</a> 
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
</html>

