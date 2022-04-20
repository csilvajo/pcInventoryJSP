<%-- 
    Document   : agregarEquipo
    Created on : 29-03-2021, 10:49:54
    Author     : Carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Agregar Equipo</title>
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
        <div class="container">
            <!-- Card General -->
            <div class="card">
                <!-- Titulo General -->
                <div class="card-header text-center">
                    <span><h4><i class="fa fa-plus-circle" aria-hidden="true"></i> Agregar Equipo</h4></span>
                </div>
                <!-- Fin Titulo General -->
                <!-- Card-Body General-->
                <div class="card-body">

                    <!-- Card que contiene el formulario -->
                    <div class="card">                        
                        <form action="FichaTecnicaController?menu=fichaTecnica" method="POST">

                            <!-- Card Identificacion del Equipo -->
                            <div class="card">
                                <div class="card-header text-center">
                                    <span></i> <strong>Identificación del Equipo</strong></span>
                                </div>
                                <div class="card-body">
                                    <!-- form group -->
                                    <div class="form-group">
                                        <!-- Fila -->
                                        <div class="row"> 
                                            <div class="col">
                                                <label>Estado Operacional: </label>
                                                <select class="form-control" id="cboEstadoOperacional" name="cboEstadoOperacional" required="true" >                            
                                                    <option selected disabled value="">- Seleccione -</option>                                
                                                    <c:forEach items="${listaEstadosOperacionales}" var="estadoOperacional"><!-- Se itera la lista -->
                                                        <option value="${estadoOperacional.getIdEstadoOperativo()}">${estadoOperacional.getEstadoOperativo()}</option><!-- Se muestran los valores del combobox -->
                                                    </c:forEach>                                                    
                                                </select>
                                            </div> 
                                            <div class="col"><!--Columna vacia --></div>
                                        </div>
                                        <!-- Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">
                                                <label>Número de serie:</label>
                                                <input type="text" name="txtNumeroSerie" class="form-control" value="" minlength="5" required="true" placeholder="Ingrese Número de serie">
                                            </div>
                                            <div class="col">
                                                <label>Tipo de Equipo: </label>
                                                <select class="form-control" id="cboTipoEquipo" name="cboTipoEquipo" required="true">                            
                                                    <option selected disabled value="">- Seleccione -</option>
                                                    <c:forEach items="${listaTiposEquipo}" var="tipoEquipo"><!-- Se itera la lista -->
                                                        <option value="${tipoEquipo.getIdTipoEquipo()}">${tipoEquipo.getTipoEquipo()}</option><!-- Se muestran los valores del combobox -->
                                                    </c:forEach> 
                                                </select>
                                            </div>                                            
                                            <div class="col">
                                                <label>Marca: </label>
                                                <select class="form-control" id="cboMarca" name="cboMarca" required="true">                            
                                                    <option selected disabled value="">- Seleccione -</option>                                
                                                    <c:forEach items="${listaMarcas}" var="marca"><!-- Se itera la lista -->
                                                        <option value="${marca.getIdMarca()}">${marca.getMarca()}</option><!-- Se muestran los valores del combobox -->
                                                    </c:forEach> 
                                                </select>  
                                            </div>
                                        </div>
                                        <!--Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">
                                                <label>Nombre del equipo:</label>
                                                <input type="text" name="txtHostname" class="form-control" value="" minlength="5" required="true" placeholder="Ingrese Nombre del equipo">
                                            </div>                                        
                                            <div class="col">
                                                <label>Empleado: </label>
                                                <select class="form-control" id="cboEmpleado" name="cboEmpleado" required="true">                            
                                                    <option selected disabled value="">- Seleccione -</option> 
                                                    <c:forEach items="${listaEmpleados}" var="empleado"><!-- Se itera la lista -->
                                                        <option value="${empleado.getIdEmpleado()}">${empleado.getNombre()} ${empleado.getApellidoPaterno()} ${empleado.getApellidoMaterno()}</option><!-- Se muestran los valores del combobox -->
                                                    </c:forEach> 
                                                </select>
                                            </div>
                                        </div>
                                        <!--Fin Fila -->
                                    </div>
                                    <!-- Fin form group -->                                
                                </div>
                            </div>                            
                            <!-- Fin Card Identificacion del Equipo -->

                            <!-- Card Información Técnica -->
                            <div class="card">
                                <div class="card-header text-center">
                                    <span><strong>Información Técnica</strong></span>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">                                                                              
                                                <select class="form-control" id="cboProcesador" name="cboProcesador" required="true">                            
                                                    <option selected disabled value="">Seleccione Tipo de Procesador: </option>                                
                                                    <c:forEach items="${listaTiposProcesador}" var="tipoProcesador"><!-- Se itera la lista -->
                                                        <option value="${tipoProcesador.getIdTipoProcesador()}">${tipoProcesador.getTipoProcesador()}</option><!-- Se muestran los valores del combobox -->
                                                    </c:forEach> 
                                                </select>
                                            </div>
                                            <div class="col">
                                                <div class="input-group mb-3">                                            
                                                    <input type="number" name="txtVelocidadProcesador" class="form-control" value="" min="1" max="4" required="true" placeholder="Velocidad del Procesador. Ej: 2 Ghz" > 
                                                    <span class="input-group-text">Ghz</span>
                                                </div>                                        
                                            </div>                                    
                                        </div>
                                        <!--Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">                                                                             
                                                <select class="form-control" id="cboRam" name="cboRam" required="true">                            
                                                    <option selected disabled value="">Seleccione Tipo de Memoria RAM: </option>                                
                                                    <c:forEach items="${listaTiposRam}" var="tipoRam"><!-- Se itera la lista -->
                                                        <option value="${tipoRam.getIdTipoRam()}">${tipoRam.getTipoRam()}</option><!-- Se muestran los valores del combobox -->
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col">    
                                                <div class="input-group mb-3"> 
                                                    <input type="number" name="txtCapacidadRam" class="form-control" value="" min="1" max="128" required="true" placeholder="Capacidad de Memoria RAM. Ej: 2 GB" >
                                                    <span class="input-group-text">GB</span>
                                                </div>
                                            </div>                                           
                                        </div>
                                        <!--Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">                                                                              
                                                <select class="form-control" id="cboDiscoDuro" name="cboDiscoDuro" required="true">                            
                                                    <option selected disabled value="">Seleccione Tipo de Disco Duro: </option>                                
                                                    <c:forEach items="${listaTiposDiscoDuro}" var="tipoDisco"><!-- Se itera la lista -->
                                                        <option value="${tipoDisco.getIdTipoDiscoDuro()}">${tipoDisco.getTipoDiscoDuro()}</option><!-- Se muestran los valores del combobox -->
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col">
                                                <div class="input-group mb-3"> 
                                                    <input type="number" name="txtCapacidadDiscoDuro" class="form-control" value="" min="128" max="8192" required="true" placeholder="Capacidad de Disco Duro. Ej: 128 GB">
                                                    <span class="input-group-text">GB</span>
                                                </div>
                                            </div>                                           
                                        </div>
                                        <!--Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">
                                                <label>Sistema Operativo: </label>                                        
                                                <select class="form-control" id="cboSistemaOperativo" name="cboSistemaOperativo" required="true">                            
                                                    <option selected disabled value="">- Seleccione -</option>                                
                                                    <c:forEach items="${listaSO}" var="so"><!-- Se itera la lista -->
                                                        <option value="${so.getIdSistemaOperativo()}">${so.getSistemaOperativo()}</option><!-- Se muestran los valores del combobox -->
                                                    </c:forEach>
                                                </select>
                                            </div>                                                                                      
                                        </div>
                                        <!--Fin Fila -->                                
                                    </div> 
                                    <!-- Fin form group -->

                                </div>
                            </div>
                            <!-- Fin Card Información Técnica -->
                            
                            <!-- Card que contiene los botones -->
                            <div class="card">
                                <br>                                
                                <div class="form-group text-center">
                                    <input type="submit" name="accion" value="Agregar" class="btn btn-success">
                                    <input type="reset" value="Limpiar" class="btn btn-info">
                                    <a class="btn btn-warning" href="FichaTecnicaController?menu=fichaTecnica&accion=Listar"><i class="fa fa-undo" aria-hidden="true"></i> Volver</a>                   
                                </div>
                            </div>
                            <!-- Fin Card que contiene los botones -->                           
                            
                        </form>                        
                    </div>
                    <!-- Fin Card que contiene el formulario -->
                </div>
                <!-- Fin Card-Body General-->                  
            </div>
            <!-- Fin Card General-->            
        </div>
        <!-- Fin Container -->
    </body>
</html>
