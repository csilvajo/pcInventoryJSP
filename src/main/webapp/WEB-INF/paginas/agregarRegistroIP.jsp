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
        <title>Agregar Registro IP</title>  
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
                    <span><h4><i class="fa fa-plus-circle" aria-hidden="true"></i> Agregar Registro IP</h4></span>
                </div>
                <div class="card-body">
                    <!-- Formulario Agregar Empleado -->
                    <form action="RegistroIPController?menu=registrosIP" method="POST">                        
                        <div class="form-group">
                            <label>Dirección IP:</label>
                            <input type="text" name="txtIP" class="form-control" value="" pattern="[+][1-254]{3}[.][1-254]{3}[.][0-254]{1-3}[.][0-254]{1-3}" title="Ejemplo : 192.168.0.1" required="true" placeholder="Ejemplo: 192.168.0.1">
                        </div>
                        <div class="form-group">
                            <label>Dirección MAC:</label>
                            <input type="text" name="txtMAC" class="form-control" value="" pattern="^[0-9A-F]{2}[-][0-9A-F]{2}[-][0-9A-F]{2}[-][0-9A-F]{2}[-][0-9A-F]{2}[-][0-9A-F]{2}$" title="Ejemplo : F8-A2-D6-4D-14-8C" required="true" placeholder="Dirección MAC" >
                        </div>
                        <div class="form-group">
                            <label>Equipo: </label>
                            <select class="form-control" id="cboEquipo" name="cboEquipo" required="true">
                                <option selected disabled value="">-Seleccione-</option>
                                <c:forEach items="${listaEquipos}" var="equipo">
                                    <option value="${equipo.getIdEquipo()}" >${equipo.getHostname()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Tipo de adaptador de red: </label>                            
                            <select class="form-control" id="cboAdaptador" name="cboAdaptador" required="true">
                                <option selected disabled value="">-Seleccione-</option>
                                <c:forEach items="${listaAdaptadoresRed}" var="adaptador">
                                    <option value="${adaptador.getIdTipoAdaptadorRed()}" >${adaptador.getTipoAdaptadorRed()}</option>
                                </c:forEach>
                            </select>  
                        </div>
                        <div class="form-group text-center">
                            <input type="submit" name="accion" value="Agregar" class="btn btn-success">
                            <input type="reset" value="Limpiar" class="btn btn-info">
                            <a class="btn btn-warning" href="RegistroIPController?menu=registrosIP&accion=Listar"><i class="fa fa-undo" aria-hidden="true"></i> Volver</a> 
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
</html>

