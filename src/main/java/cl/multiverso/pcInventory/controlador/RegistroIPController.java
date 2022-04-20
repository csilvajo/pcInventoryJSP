package cl.multiverso.pcInventory.controlador;

import cl.multiverso.pcInventory.dao.EquipoDAO;
import cl.multiverso.pcInventory.dao.RegistroIPDAO;
import cl.multiverso.pcInventory.dao.TipoAdaptadorRedDAO;
import cl.multiverso.pcInventory.modelo.Equipo;
import cl.multiverso.pcInventory.modelo.RegistroIP;
import cl.multiverso.pcInventory.modelo.TipoAdaptadorRed;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet RegistroIPController. 10/05/2021
 *
 * @author Carlos
 */
@WebServlet(name = "RegistroIPController", urlPatterns = {"/RegistroIPController"})
public class RegistroIPController extends HttpServlet {
    
    RegistroIP objRegistroIP = new RegistroIP();
    RegistroIPDAO objRegistroIPDAO = new RegistroIPDAO();
    Equipo objEquipo = new Equipo();
    EquipoDAO objEquipoDAO = new EquipoDAO();
    TipoAdaptadorRed objTipoAdaptadorRed = new TipoAdaptadorRed();
    TipoAdaptadorRedDAO objTipoAdaptadorRedDAO = new TipoAdaptadorRedDAO();

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

        if (menu.equals("registrosIP")) {
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
                    //this.seleccionar(request, response);
                    break;
                case "Actualizar":
                    //this.actualizar(request, response);
                    break;
                case "Eliminar":
                    //this.eliminar(request, response);
                    break;
                case "SeleccionaParaEditarPassword":
                    //this.seleccionaParaEditarPassword(request, response);
                    break;
                case "Enviar":
                    //this.editarPassword(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/WEB-INF/paginas/registroIP.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/paginas/registroIP.jsp").forward(request, response);
        }//fin if

    }//fin metodo

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Metodo que permite listar los regsitros de ip
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se setean las listas de registro de ip, equipo y tipo de adaptador de red
        List listaRegistrosIP = this.objRegistroIPDAO.listar();

        if (listaRegistrosIP.isEmpty()) {
            //si la lista esta vacia se envia el mensaje a la pagina jsp
            request.setAttribute("mensaje", "No hay registros");
        } else {
            //se envian las listas al alcance de la pagina jsp
            request.setAttribute("listaRegistrosIP", listaRegistrosIP);
        }
    }//fin metodo
    
    /**
     * Metodo que permite abrir la pagina para agregar un registro de IP
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void abrirPaginaAgregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se setea y se envia las listas de equipo y tipo de adaptador de red
        request.setAttribute("listaEquipos", this.objEquipoDAO.listar());
        request.setAttribute("listaAdaptadoresRed", this.objTipoAdaptadorRedDAO.listar());
        request.getRequestDispatcher("/WEB-INF/paginas/agregarRegistroIP.jsp").forward(request, response);
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
            String direccionMAC = request.getParameter("txtMAC");
            String direccionIP = request.getParameter("txtIP");
            int idEquipo = Integer.parseInt(request.getParameter("cboEquipo"));
            int idTipoAdaptadorRed = Integer.parseInt(request.getParameter("cboAdaptador"));

            //se setean la variables del objeto
            this.objRegistroIP.setDireccionMAC(direccionMAC);
            this.objRegistroIP.setDireccionIP(direccionIP);
            this.objEquipo.setIdEquipo(idEquipo);
            this.objRegistroIP.setObjEquipo(objEquipo);
            this.objTipoAdaptadorRed.setIdTipoAdaptadorRed(idTipoAdaptadorRed);
            this.objRegistroIP.setObjTipoAdaptadorRed(objTipoAdaptadorRed);

            //se verifica si el registro de IP ya existe en la tabla
            boolean registroIPExiste = this.objRegistroIPDAO.validaRegistroIP(this.objRegistroIP);

            //si el registro no existe en la tabla, se puede agregar el nuevo registro
            if (registroIPExiste == false) {
                //se envia el objeto RegistroIP al metodo agregar de DAO 
                this.objRegistroIPDAO.agregar(this.objRegistroIP);
                //se envia el mensaje a la pagina jsp
                request.setAttribute("mensaje", this.objRegistroIPDAO.getMensaje());
                //se actualiza la tabla del mantenedor
                request.getRequestDispatcher("RegistroIPController?menu=registrosIP&accion=Listar").forward(request, response);
            } else {
                //se genera un mensaje de error y se envia el mensaje a la pagina jsp 
                request.setAttribute("mensaje", this.objRegistroIPDAO.getMensaje());
                //se actualiza la tabla del mantenedor
                request.getRequestDispatcher("RegistroIPController?menu=registrosIP&accion=Listar").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("mensaje", "-Error- No se puede procesar la operación" + ex.getMessage());//se envia el mensaje a la pagina jsp
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
    }

    // </editor-fold>
}//fin Servlet
