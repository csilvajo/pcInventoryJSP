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
        <title>Agregar Empleado</title>  
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
                    <span><h4><i class="fa fa-plus-circle" aria-hidden="true"></i> Agregar Empleado</h4></span>
                </div>
                <div class="card-body">
                    <!-- Formulario Agregar Empleado -->
                    <form action="EmpleadoController?menu=empleados" method="POST">
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" name="txtNombre" class="form-control" value="" minlength="4" required="true" placeholder="Nombre" >
                        </div> 
                        <div class="form-group">
                            <label>Apellido Paterno:</label>
                            <input type="text" name="txtApellidoPaterno" class="form-control" value="" minlength="4" required="true" placeholder="Apellido paterno">
                        </div>
                        <div class="form-group">
                            <label>Apellido Materno:</label>
                            <input type="text" name="txtApellidoMaterno" class="form-control" value="" minlength="4" required="true" placeholder="Apellido materno">
                        </div>
                        <div class="form-group">
                            <label>Teléfono:</label>
                            <input type="tel" name="txtTelefono" class="form-control" value="" pattern="[+][0-9]{2}[-][0-9]{1}[-][0-9]{8}" title="Ejemplo : +56-2-55354704" required="true" placeholder="+56-2-55354704">
                        </div>
                        <div class="form-group">
                            <label>Email:</label>
                            <input type="email" name="txtEmail" class="form-control" value="" pattern="[a-zA-Z0-9_]*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"required="true" placeholder="correo@mail.com">
                        </div>
                        <div class="form-group">
                            <label>Username:</label>
                            <input type="text" name="txtUsername" class="form-control" value="" minlength="4" required="true" placeholder="Nombre de usuario">
                        </div>
                        <div class="form-group">
                            <label>Password:</label>
                            <input type="password" name="txtPassword" class="form-control" value="" minlength="5" maxlength="10" required="true" placeholder="Contraseña">
                        </div>
                        <div class="form-group">
                            <label>Tipo de Usuario: </label>
                            <select class="form-control" id="cboTipoUsuario" name="cboTipoUsuario" required="true">
                                <option selected disabled value="">-Seleccione-</option>
                                <c:forEach items="${listaTiposUsuario}" var="tipoUsuario">
                                    <option value="${tipoUsuario.getIdTipoUsuario()}" >${tipoUsuario.getTipoUsuario()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Unidad: </label>                            
                            <select class="form-control" id="cboUnidad" name="cboUnidad" required="true">
                                <option selected disabled value="">-Seleccione-</option>
                                <c:forEach items="${listaUnidades}" var="unidad">
                                    <option value="${unidad.getIdUnidad()}" >${unidad.getUnidad()}</option>
                                </c:forEach>
                            </select>  
                        </div>
                        <div class="form-group text-center">
                            <input type="submit" name="accion" value="Agregar" class="btn btn-success">
                            <input type="reset" value="Limpiar" class="btn btn-info">
                            <a class="btn btn-warning" href="EmpleadoController?menu=empleados&accion=Listar"><i class="fa fa-undo" aria-hidden="true"></i> Volver</a> 
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
</html>

