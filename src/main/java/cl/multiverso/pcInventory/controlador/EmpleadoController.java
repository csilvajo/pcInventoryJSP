package cl.multiverso.pcInventory.controlador;

import cl.multiverso.pcInventory.dao.EmpleadoDAO;
import cl.multiverso.pcInventory.dao.EstadoOperativoDAO;
import cl.multiverso.pcInventory.dao.TipoUsuarioDAO;
import cl.multiverso.pcInventory.dao.UnidadDAO;
import cl.multiverso.pcInventory.modelo.Empleado;
import cl.multiverso.pcInventory.modelo.EstadoOperativo;
import cl.multiverso.pcInventory.modelo.TipoUsuario;
import cl.multiverso.pcInventory.modelo.Unidad;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet EmpleadoController. 16/04/2021
 *
 * @author Carlos
 */
@WebServlet(name = "EmpleadoController", urlPatterns = {"/EmpleadoController"})
public class EmpleadoController extends HttpServlet {

    Empleado objEmpleado = new Empleado();
    EmpleadoDAO objEmpleadoDAO = new EmpleadoDAO();
    TipoUsuario objTipoUsuario = new TipoUsuario();
    TipoUsuarioDAO objTipoUsuarioDAO = new TipoUsuarioDAO();
    Unidad objUnidad = new Unidad();
    UnidadDAO objUnidadDAO = new UnidadDAO();

    EstadoOperativo objEstadoOperativo = new EstadoOperativo();
    EstadoOperativoDAO objEstadoOperativoDAO = new EstadoOperativoDAO();
    int id;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("empleados")) {
            switch (accion) {
                case "Listar":
                    this.listar(request, response);
                    break;
                case "AbrirPagina":
                    this.abrirPaginaAgregar(request, response);
                    break;
                case "Agregar":
                    this.agregar(request, response);
                    break;
                case "Editar":
                    this.seleccionar(request, response);
                    break;
                case "Actualizar":
                    this.actualizar(request, response);
                    break;
                case "Eliminar":
                    this.eliminar(request, response);
                    break;
                case "SeleccionaParaEditarPassword":
                    this.seleccionaParaEditarPassword(request, response);
                    break;
                case "Enviar":
                    this.editarPassword(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/WEB-INF/paginas/empleados.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/paginas/empleados.jsp").forward(request, response);
        }//fin if

    }//fin metodo

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Metodo que permite listar los empleados
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se setean las listas de empleados 
        List listaEmpleados = this.objEmpleadoDAO.listar();

        if (listaEmpleados.isEmpty()) {
            //si la lista esta vacia se envia el mensaje a la pagina jsp
            request.setAttribute("mensaje", "No hay registros");
        } else {
            //se envian las listas al alcance de la pagina jsp
            request.setAttribute("listaEmpleados", listaEmpleados);
        }
    }//fin metodo

    /**
     * Metodo que permite abrir la pagina para agregar un empleado
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void abrirPaginaAgregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se setea y se envia las listas de tipos de usuario y unidad a la pagina agregarEmpleado.jsp, en el alcance de request.
        request.setAttribute("listaTiposUsuario", this.objTipoUsuarioDAO.listar());
        request.setAttribute("listaUnidades", this.objUnidadDAO.listar());
        request.getRequestDispatcher("/WEB-INF/paginas/agregarEmpleado.jsp").forward(request, response);
    }//fin metodo

    /**
     * Metodo que permite agregar un nuevo registro
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //se capturan los datos ingresados en el formulario de la pagina empleados.jsp 
            String nombre = request.getParameter("txtNombre");
            String apellidoPaterno = request.getParameter("txtApellidoPaterno");
            String apellidoMaterno = request.getParameter("txtApellidoMaterno");
            String telefono = request.getParameter("txtTelefono");
            String email = request.getParameter("txtEmail");
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            int idTipoUsuario = Integer.parseInt(request.getParameter("cboTipoUsuario"));
            int idUnidad = Integer.parseInt(request.getParameter("cboUnidad"));

            //se setean la variables del objeto
            this.objEmpleado.setNombre(nombre);
            this.objEmpleado.setApellidoPaterno(apellidoPaterno);
            this.objEmpleado.setApellidoMaterno(apellidoMaterno);
            this.objEmpleado.setTelefono(telefono);
            this.objEmpleado.setEmail(email);
            this.objEmpleado.setUsername(username);
            this.objEmpleado.setPassword(password);
            this.objTipoUsuario.setIdTipoUsuario(idTipoUsuario);
            this.objEmpleado.setObjTipoUsuario(this.objTipoUsuario);
            this.objUnidad.setIdUnidad(idUnidad);
            this.objEmpleado.setObjUnidad(this.objUnidad);

            //se verifica si el empleado ya existe en la tabla tipo empleado
            boolean empleadoExiste = this.objEmpleadoDAO.validaEmpleado(objEmpleado);

            //si el registro no existe en la tabla, se puede agregar el nuevo registro
            if (empleadoExiste == false) {
                //se envia el objeto Empleado al metodo agregar de DAO 
                this.objEmpleadoDAO.agregar(this.objEmpleado);
                //se envia el mensaje a la pagina jsp
                request.setAttribute("mensaje", this.objEmpleadoDAO.getMensaje());
                //se actualiza la tabla del mantenedor
                request.getRequestDispatcher("EmpleadoController?menu=empleados&accion=Listar").forward(request, response);
            } else {
                //se genera un mensaje de error y se envia el mensaje a la pagina jsp 
                request.setAttribute("mensaje", this.objEmpleadoDAO.getMensaje());
                //se actualiza la tabla del mantenedor
                request.getRequestDispatcher("EmpleadoController?menu=empleados&accion=Listar").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("mensaje", "-Error- No se puede procesar la operación" + ex.getMessage());//se envia el mensaje a la pagina jsp
        }
    }//fin metodo 

    /**
     * Metodo que permite seleccionar un registro
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void seleccionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se recoge el id seleccionado con el boton Editar en la tabla
        this.id = Integer.parseInt(request.getParameter("id"));
        //el objeto Empleado recoge el valor obtenido por el seleccionarPorId del objeto DAO 
        this.objEmpleado = this.objEmpleadoDAO.seleccionarPorId(this.id);
        //se envian los datos al formulario que actualiza registros , en el alcance de request
        request.setAttribute("empleado", this.objEmpleado);
        //se setean y se envian las listas de tipos de usuario y unidad a la pagina editarEmpleado.jsp, en el alcance de request.
        request.setAttribute("listaTiposUsuario", objTipoUsuarioDAO.listar());
        request.setAttribute("listaUnidades", objUnidadDAO.listar());
        request.getRequestDispatcher("/WEB-INF/paginas/editarEmpleado.jsp").forward(request, response);
    }//fin metodo

    /**
     * Metodo que permite actualizar un registro
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //se capturan los datos ingresados en el formulario de la pagina empleados.jsp 
            String nombre = request.getParameter("txtNombre");
            String apellidoPaterno = request.getParameter("txtApellidoPaterno");
            String apellidoMaterno = request.getParameter("txtApellidoMaterno");
            String telefono = request.getParameter("txtTelefono");
            String email = request.getParameter("txtEmail");
            String username = request.getParameter("txtUsername");
            int idTipoUsuario = Integer.parseInt(request.getParameter("cboTipoUsuario"));
            int idUnidad = Integer.parseInt(request.getParameter("cboUnidad"));

            //se setean la variables del objeto
            this.objEmpleado.setNombre(nombre);
            this.objEmpleado.setApellidoPaterno(apellidoPaterno);
            this.objEmpleado.setApellidoMaterno(apellidoMaterno);
            this.objEmpleado.setTelefono(telefono);
            this.objEmpleado.setEmail(email);
            this.objEmpleado.setUsername(username);
            this.objTipoUsuario.setIdTipoUsuario(idTipoUsuario);
            this.objEmpleado.setObjTipoUsuario(this.objTipoUsuario);
            this.objUnidad.setIdUnidad(idUnidad);
            this.objEmpleado.setObjUnidad(this.objUnidad);

            //se envia el objeto Empleado al metodo actualizar de DAO 
            this.objEmpleadoDAO.actualizar(this.objEmpleado);
            //se envia el mensaje a la pagina jsp
            request.setAttribute("mensaje", this.objEmpleadoDAO.getMensaje());
            //se actualiza la tabla del mantenedor
            request.getRequestDispatcher("EmpleadoController?menu=empleados&accion=Listar").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("mensaje", "-Error- No se puede procesar la operación" + ex.getMessage());//se envia el mensaje a la pagina jsp
        }
    }//fin metodo

    /**
     * Metodo que permite eliminar un registro
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.id = Integer.parseInt(request.getParameter("id"));//se recoge el id seleccionado con el boton Eliminar en la tabla
        this.objEmpleadoDAO.eliminar(this.id);//se elimina el registro utilizando el metodo eliminar del objeto DAO       
        request.setAttribute("mensaje", this.objEmpleadoDAO.getMensaje());//se envia el mensaje a la pagina jsp 
        request.getRequestDispatcher("EmpleadoController?menu=empleados&accion=Listar").forward(request, response);//se actualiza la tabla del mantenedor
    }//fin metodo

    /**
     * Metodo que permite seleccionar un empleado para editar la password
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void seleccionaParaEditarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.id = Integer.parseInt(request.getParameter("id"));//se recoge el id seleccionado con el boton Editar en la tabla
        this.objEmpleado = this.objEmpleadoDAO.seleccionarPorId(id);//el objeto Empleado recoge el valor obtenido por el seleccionarPorId del objeto EmpleadoDAO
        request.setAttribute("empleado", this.objEmpleado);//se envian los datos al formulario que actualiza registros
        request.getRequestDispatcher("/WEB-INF/paginas/actualizaPassword.jsp").forward(request, response);
    }//fin metodo

    /**
     * Metodo que permite seleccionar un empleado para editar la password
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void editarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se capturan los datos ingresados en el formulario de la pagina empleados.jsp                     
        String passwordUpdate = request.getParameter("txtPassword");
        //se setea la variable con el dato ingresado
        this.objEmpleado.setPassword(passwordUpdate);
        //se envia el objeto Empleado al metodo actualizarPassword de EmpleadoDAO 
        this.objEmpleadoDAO.actualizarPassword(this.objEmpleado);
        //se setea y se envia el mensaje a la pagina jsp 
        request.setAttribute("mensaje", this.objEmpleadoDAO.getMensaje());
        //se actualiza la tabla del mantenedor
        request.getRequestDispatcher("EmpleadoController?menu=empleados&accion=Listar").forward(request, response);
    }//fin metodo

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }//fin metodo

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }//fin metodo

    // </editor-fold>
}//fin servlet
