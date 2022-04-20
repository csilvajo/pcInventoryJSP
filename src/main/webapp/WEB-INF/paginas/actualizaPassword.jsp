<%-- 
    Document   : prueba
    Created on : 24-03-2021, 21:58:58
    Author     : Carlos
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Actualizar Empleado</title>  
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Se importa Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <!-- Se importan Iconos de Font Awesome -->
        <script src="https://kit.fontawesome.com/a03d9c12dd.js" crossorigin="anonymous"></script>         
    </head>
    <body>
        <div class="container mt-4 col-lg-4">
            <div class="card">
                <!-- Titulo -->
                <div class="card-header text-center">
                    <h4><i class="fa fa-key" aria-hidden="true"></i> Actualizar contraseña</h4> 
                </div>
                <!-- Fin Titulo -->
                <!-- Formulario actualiza password -->
                <div class="card-body">                   
                    <form action="EmpleadoController?menu=empleados" method="POST">
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" name="txtNombres" class="form-control" value="${empleado.getNombre()} ${empleado.getApellidoPaterno()} ${empleado.getApellidoMaterno()}" readonly="true">
                        </div>                         
                        <div class="form-group">
                            <label>Username:</label>
                            <input type="text" name="txtUsername" class="form-control" value="${empleado.getUsername()}" readonly="true">
                        </div>
                        <div class="form-group">
                            <label>Password:</label>
                            <input type="password" name="txtPassword" class="form-control" value="" minlength="5" maxlength="10" required="true" placeholder="Ingresa la nueva Contraseña">
                        </div>

                        <div class="form-group text-center">
                            <input type="submit" name="accion" value="Enviar" class="btn btn-success">    
                            <input type="reset" value="Limpiar" class="btn btn-info">
                            <a class="btn btn-warning" href="EmpleadoController?menu=empleados&accion=Listar"><i class="fa fa-undo" aria-hidden="true"></i> Volver</a> 
                        </div>
                    </form>
                </div>
                <!--Fin Formulario actualiza password -->
            </div>
        </div>
    </body>
</html>

