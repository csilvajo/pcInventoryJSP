<%-- 
    Document   : agregarEquipo
    Created on : 29-03-2021, 10:49:54
    Author     : Carlos
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <title>Actualizar Equipo</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Se importa Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <!-- Se importan Iconos de Font Awesome -->
        <script src="https://kit.fontawesome.com/a03d9c12dd.js" crossorigin="anonymous"></script> 
    </head>
    <body>
        <div class="container">
            <div class="card">
                <!-- Titulo -->
                <div class="card-header text-center">
                    <span><h4><i class="fa fa-pencil-square" aria-hidden="true"></i> Actualizar Equipo</h4></span>
                </div>
                <!-- Fin Titulo -->
                <!-- Card-Body -->
                <div class="card-body">
                    <form action="FichaTecnicaController?menu=fichaTecnica" method="POST">
                        <!-- Container -->
                        <div class="container">
                            <div class="card">
                                <div class="card-header text-center">
                                    <span><strong>Identificación del Equipo</strong></span>
                                </div>
                                <div class="card-body">
                                    <!-- form group -->
                                    <div class="form-group">
                                        <!-- Fila -->
                                        <div class="row"> 
                                            <div class="col">
                                                <label>Estado Operacional: </label>                                                
                                                <select class="form-control" id="cboEstadoOperacional" name="cboEstadoOperacional" required="true">                                                    
                                                    <option selected disabled value="">Seleccione Estado Operacional</option>                               
                                                    <c:forEach items="${listaEstadosOperacionales}" var="estadoOperacional"><!-- Se itera la lista -->
                                                        <!-- Elige, cuando equipo.idTipo == tipo.idTipo adquiere el valor actual, si no selecciona nuevo valor -->
                                                        <c:choose>
                                                            <c:when test="${equipo.getObjEstadoOperativo().getIdEstadoOperativo() == estadoOperacional.getIdEstadoOperativo()}">
                                                                <option value="${estadoOperacional.getIdEstadoOperativo()}" selected >${estadoOperacional.getEstadoOperativo()}</option><!-- Muestra el valor del tipo -->
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${estadoOperacional.getIdEstadoOperativo()}" >${estadoOperacional.getEstadoOperativo()}</option><!-- Se muestran los valores del combobox -->
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach> 
                                                </select>
                                            </div> 
                                            <div class="col">
                                                <label>Id Equipo:</label>
                                                <input type="text" name="txtId" class="form-control" value="${equipo.getIdEquipo()}" readonly>
                                            </div> 
                                        </div>
                                        <!-- Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">
                                                <label>Número de serie:</label>
                                                <input type="text" name="txtNumeroSerie" class="form-control" value="${equipo.getNumeroSerie()}" readonly >
                                            </div>
                                            <div class="col">
                                                <label>Tipo de Equipo: </label> 
                                                <input type="text" name="txtTipoEquipo" class="form-control" value="${equipo.getObjTipoEquipo().getTipoEquipo()}" readonly >                                                
                                            </div>                                            
                                            <div class="col">
                                                <label>Marca: </label>
                                                <input type="text" name="txtMarca" class="form-control" value="${equipo.getObjMarca().getMarca()}" readonly >
                                            </div>
                                        </div>
                                        <!--Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">
                                                <label>Nombre del equipo:</label>
                                                <input type="text" name="txtHostname" class="form-control" value="${equipo.getHostname()}" required="true" placeholder="Ingrese Nombre del equipo">
                                            </div>                                            
                                            <div class="col">
                                                <label>Empleado: </label>                                                
                                                <select class="form-control" id="cboEmpleado" name="cboEmpleado" required="true">                            
                                                    <option selected disabled value="">Seleccione Empleado</option>                                 
                                                    <c:forEach items="${listaEmpleados}" var="empleado"><!-- Se itera la lista -->
                                                        <!-- Elige, cuando equipo.idTipo == tipo.idTipo adquiere el valor actual, si no selecciona nuevo valor -->
                                                        <c:choose>
                                                            <c:when test="${equipo.getObjEmpleado().getIdEmpleado() == empleado.getIdEmpleado()}">
                                                                <option value="${empleado.getIdEmpleado()}" selected >${empleado.getNombre()} ${empleado.getApellidoPaterno()} ${empleado.getApellidoMaterno()}</option><!-- Muestra el valor del tipo -->
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${empleado.getIdEmpleado()}" >${empleado.getNombre()} ${empleado.getApellidoPaterno()} ${empleado.getApellidoMaterno()}</option><!-- Se muestran los valores del combobox -->
                                                            </c:otherwise>
                                                        </c:choose>  
                                                    </c:forEach> 
                                                </select>
                                            </div>
                                        </div>
                                        <!--Fin Fila -->
                                    </div> 
                                    <!-- Fin form group -->
                                </div>
                                <!-- Fin card body -->
                            </div>
                            <!-- Fin Card -->
                        </div>
                        <!-- Fin container -->

                        <!-- Container -->
                        <div class="container">
                            <div class="card">
                                <div class="card-header text-center">
                                    <span><strong>Información Técnica</strong></span>
                                </div>
                                <div class="card-body">
                                    <!-- form group -->
                                    <div class="form-group">
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">
                                                <label>Tipo de Procesador: </label>                                                
                                                <select class="form-control" id="cboProcesador" name="cboProcesador" required="true">                            
                                                    <option selected disabled value="">Seleccione Tipo de Procesador</option>                                 
                                                    <c:forEach items="${listaTiposProcesador}" var="tipoProcesador"><!-- Se itera la lista -->
                                                        <!-- Elige, cuando equipo.idTipo == tipo.idTipo adquiere el valor actual, si no selecciona nuevo valor -->
                                                        <c:choose>
                                                            <c:when test="${equipo.getObjTipoProcesador().getIdTipoProcesador() == tipoProcesador.getIdTipoProcesador()}">
                                                                <option value="${tipoProcesador.getIdTipoProcesador()}" selected >${tipoProcesador.getTipoProcesador()}</option><!-- Muestra el valor del tipo -->
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${tipoProcesador.getIdTipoProcesador()}">${tipoProcesador.getTipoProcesador()}</option><!-- Se muestran los valores del combobox -->
                                                            </c:otherwise>
                                                        </c:choose>                                                      
                                                    </c:forEach> 
                                                </select>
                                            </div>
                                            <div class="col">
                                                <label>Velocidad del procesador:</label>
                                                <input type="number" name="txtVelocidadProcesador" class="form-control" value="${equipo.getVelocidadProcesador()}" required="true" placeholder="Velocidad del procesador">
                                            </div>                                           
                                        </div>
                                        <!--Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">
                                                <label>Tipo de Memoria RAM: </label>                                               
                                                <select class="form-control" id="cboRam" name="cboRam" required="true">                            
                                                    <option selected disabled value="">Seleccione Tipo de Memoria RAM</option>                                      
                                                    <c:forEach items="${listaTiposRam}" var="tipoRam"><!-- Se itera la lista -->
                                                        <!-- Elige, cuando equipo.idTipo == tipo.idTipo adquiere el valor actual, si no selecciona nuevo valor -->
                                                        <c:choose>
                                                            <c:when test="${equipo.getObjTipoRam().getIdTipoRam() == tipoRam.getIdTipoRam()}">
                                                                <option value="${tipoRam.getIdTipoRam()}" selected >${tipoRam.getTipoRam()}</option><!-- Muestra el valor del tipo -->
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${tipoRam.getIdTipoRam()}" >${tipoRam.getTipoRam()}</option><!-- Se muestran los valores del combobox -->
                                                            </c:otherwise>
                                                        </c:choose>  
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col">
                                                <label>Capacidad de Memoria RAM:</label>
                                                <input type="number" name="txtCapacidadRam" class="form-control" value="${equipo.getCapacidadRam()}" required="true" placeholder="Capacidad de Memoria RAM">
                                            </div>                                           
                                        </div>
                                        <!--Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">
                                                <label>Tipo de Disco Duro: </label>                                                
                                                <select class="form-control" id="cboDiscoDuro" name="cboDiscoDuro" required="true">                            
                                                    <option selected disabled value="">Seleccione Tipo de Disco Duro</option>                               
                                                    <c:forEach items="${listaTiposDiscoDuro}" var="tipoDisco"><!-- Se itera la lista -->
                                                        <!-- Elige, cuando equipo.idTipo == tipo.idTipo adquiere el valor actual, si no selecciona nuevo valor -->
                                                        <c:choose>
                                                            <c:when test="${equipo.getObjTipoDiscoDuro().getIdTipoDiscoDuro() == tipoDisco.getIdTipoDiscoDuro()}">
                                                                <option value="${tipoDisco.getIdTipoDiscoDuro()}" selected >${tipoDisco.getTipoDiscoDuro()}</option><!-- Muestra el valor del tipo -->
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${tipoDisco.getIdTipoDiscoDuro()}" >${tipoDisco.getTipoDiscoDuro()}</option><!-- Se muestran los valores del combobox -->
                                                            </c:otherwise>
                                                        </c:choose> 
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col">
                                                <label>Capacidad de Disco Duro:</label>
                                                <input type="number" name="txtCapacidadDiscoDuro" class="form-control"  value="${equipo.getCapacidadDiscoDuro()}" required="true" placeholder="Capacidad de Disco Duro">
                                            </div>                                           
                                        </div>
                                        <!--Fin Fila -->
                                        <br>
                                        <!-- Fila -->
                                        <div class="row">
                                            <div class="col">
                                                <label>Sistema Operativo: </label>                                               
                                                <select class="form-control" id="cboSistemaOperativo" name="cboSistemaOperativo" required="true">                            
                                                    <option selected disabled value="">Seleccione Sistema Operativo</option>                               
                                                    <c:forEach items="${listaSO}" var="so"><!-- Se itera la lista -->
                                                        <!-- Elige, cuando equipo.idTipo == tipo.idTipo adquiere el valor actual, si no selecciona nuevo valor -->
                                                        <c:choose>
                                                            <c:when test="${equipo.getObjSistemaOperativo().getIdSistemaOperativo() == so.getIdSistemaOperativo()}">
                                                                <option value="${so.getIdSistemaOperativo()}" selected >${so.getSistemaOperativo()}</option><!-- Muestra el valor del tipo -->
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${so.getIdSistemaOperativo()}" >${so.getSistemaOperativo()}</option><!-- Se muestran los valores del combobox -->
                                                            </c:otherwise>
                                                        </c:choose> 
                                                    </c:forEach>
                                                </select>
                                            </div>                                                                                      
                                        </div>
                                        <!--Fin Fila -->
                                    </div> 
                                    <!-- Fin form group -->
                                </div>
                                <!-- Fin card body -->
                            </div>
                            <!-- Fin Card -->
                        </div>
                        <!-- Fin container -->
                        <br>
                        <!-- Botones -->
                        <div class="form-group text-center">
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                            <a class="btn btn-warning" href="FichaTecnicaController?menu=fichaTecnica&accion=Listar"><i class="fa fa-undo" aria-hidden="true"></i> Volver</a> 
                        </div>
                    </form>
                </div>
                <!-- Fin Card-Body -->
            </div>  
            <!-- Fin Card -->
        </div>
        <!-- Fin Container -->
    </body>
</html>
