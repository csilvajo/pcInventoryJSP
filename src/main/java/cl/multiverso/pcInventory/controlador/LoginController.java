package cl.multiverso.pcInventory.controlador;

import cl.multiverso.pcInventory.dao.EmpleadoDAO;
import cl.multiverso.pcInventory.modelo.Empleado;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet LoginController. 05/04/2021
 *
 * @author Carlos
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    EmpleadoDAO objEmpleadoDAO;
    HttpSession objSession;

    /**
     * Metodo conttructor
     */
    public LoginController() {
        this.objEmpleadoDAO = new EmpleadoDAO();
    }//fin constructor

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

        String accion = request.getParameter("accion");

        switch (accion) {
            case "Ingresar":
                this.validar(request, response);
                break;
            case "Salir":
                this.salir(request, response);
                break;
        }//fin switch

    }//fin metodo

    /**
     * Metodo que procesa el login
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se instancia el objeto Empleado
        Empleado objEmpleado = new Empleado();
        String mensaje;

        //se setean las variable username y password con los datos ingresados en la pagina index.jsp
        objEmpleado.setUsername(request.getParameter("txtUser"));
        objEmpleado.setPassword(request.getParameter("txtPassword"));

        try {
            //se valida si el empleado existe
            if (this.objEmpleadoDAO.validaLogin(objEmpleado)) {
                //se activa la sesion del Empleado logeado
                this.objSession = request.getSession(true);
                //se crean los atributos de la sesion
                Empleado empleado = new Empleado();
                empleado = this.objEmpleadoDAO.empleadoLogeado(objEmpleado);

                this.objSession.setAttribute("Nombre", empleado.getNombre());
                this.objSession.setAttribute("ApellidoPaterno", empleado.getApellidoPaterno());
                this.objSession.setAttribute("ApellidoMaterno", empleado.getApellidoMaterno());
                this.objSession.setAttribute("Usuario", empleado.getUsername());
                this.objSession.setAttribute("Telefono", empleado.getTelefono());
                this.objSession.setAttribute("Email", empleado.getEmail());
                this.objSession.setAttribute("TipoUsuario", empleado.getObjTipoUsuario().getTipoUsuario());
                this.objSession.setAttribute("Unidad", empleado.getObjUnidad().getUnidad());

                //se direcciona al Controlador de Home, con las variables de sesion
                request.getRequestDispatcher("HomeController?menu=home").forward(request, response);
            } else {
                //si las credenciales no son validas se muestra un mensaje de error en la pagina Index
                mensaje = this.objEmpleadoDAO.getMensaje();
                request.setAttribute("mensaje", mensaje);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("mensaje", "-Error- No se puede procesar la solicitud : " + ex.getMessage());
        }
    }//fin metodo

    /**
     * Metodo que invalida la sesion activa
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws java.io.IOException
     */
    protected void salir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (this.objSession != null) {
                this.objSession.invalidate();
                response.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {
            request.setAttribute("mensaje", "-Error- Debe volver a iniciar sesión ");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }//fin metodo    

}//fin servlet
