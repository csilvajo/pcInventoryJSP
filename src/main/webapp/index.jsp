<%-- 
    Document   : index
    Created on : 09-03-2021, 15:57:16
    Author     : Carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Se importa Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- Se importan Iconos de Font Awesome -->
        <script src="https://kit.fontawesome.com/a03d9c12dd.js" crossorigin="anonymous"></script>

        <style>
            html,
            body {
                height: 100%;
            }
            body {
                display: flex;
                align-items: center;
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }
        </style>
    </head>
    <body class="text-center">        
        <div class="container mt-4 col-lg-4">            
            <div class="card">                
                <!-- Titulo -->
                <div class="card-header text-center bg-info text-white">
                    <h4>PC Inventory</h4> 
                </div>
                <!-- Fin Titulo -->
                <div class="card-body">
                    <!-- Formulario Login -->
                    <!--<form class="form-sign" action="LoginControlador" method="POST">-->
                    <form class="form-sign" action="LoginController" method="POST">
                        <div class="form-group text-center">
                            <img src="img/fichatecnica.png" alt="100" width="170"/>                            
                            <br><br>
                            <h1><i class="fa fa-sign-in"></i> Login</h1>                           
                        </div>   
                        <div class="form-group">
                            <input type="text" name="txtUser" class="form-control" placeholder="Ingresa tu Usuario" required autofocus >
                        </div>   
                        <div class="form-group">
                            <input type="password" name="txtPassword" class="form-control" placeholder="Ingresa tu Contraseña" required >
                        </div>
                        <c:if test="${not empty requestScope.mensaje}">
                            <div class="alert alert-danger" role="alert">
                                <button type="button" class="close" data-dismiss="alert"><i class="fa fa-window-close"></i></button>
                                <strong>${requestScope.mensaje}</strong><!-- Mensaje de resultado de agregar o actualizar el registro --> 
                            </div>
                        </c:if>
                        <br>
                        <div class="form-group text-center">
                            <input type="submit" name="accion" value="Ingresar" class="w-100 btn btn-lg btn-success">
                        </div>
                        <p class="mt-5 mb-3 text-muted">&copy; 2021 Multiverso.com</p>
                    </form>                     
                </div>                
            </div>
        </div>

        <!-- Se importa Bootstrap JS -->   
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>    
        <!-- Se importa jQuery,Popper.js y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    </body>
</html>
