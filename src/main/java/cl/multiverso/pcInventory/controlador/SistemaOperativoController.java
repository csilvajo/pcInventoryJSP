package cl.multiverso.pcInventory.controlador;

import cl.multiverso.pcInventory.dao.EstadoOperativoDAO;
import cl.multiverso.pcInventory.dao.SistemaOperativoDAO;
import cl.multiverso.pcInventory.modelo.EstadoOperativo;
import cl.multiverso.pcInventory.modelo.SistemaOperativo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet SistemaOperativoController. 16/04/2021
 *
 * @author Carlos
 */
@WebServlet(name = "SistemaOperativoController", urlPatterns = {"/SistemaOperativoController"})
public class SistemaOperativoController extends HttpServlet {

    SistemaOperativo objSistemaOperativo = new SistemaOperativo();
    SistemaOperativoDAO objSistemaOperativoDAO = new SistemaOperativoDAO();
    
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

        if (menu.equals("sistemasOperativos")) {
            switch (accion) {
                case "Listar":
                    this.listar(request, response);
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
                default:
                    request.getRequestDispatcher("/WEB-INF/paginas/sistemasOperativos.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/paginas/sistemasOperativos.jsp").forward(request, response);
        }//fin if

    }//fin metodo

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Metodo que permite listar los tipos de usuario
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List listaSistemasOperativos = this.objSistemaOperativoDAO.listar();//la variable listaSistemasOperativos almacena la lista Sistemas Operativos que recupera el objeto DAO
        if (listaSistemasOperativos.isEmpty()) {
            request.setAttribute("mensaje", "No hay registros");//se envia el mensaje a la pagina jsp
        } else {
            request.setAttribute("listaSistemasOperativos", listaSistemasOperativos);//se envia la lista al mantenedor en la pagina jsp
        }
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
            String sistemaOperativo = request.getParameter("txtSistemaOperativo");//se captura el sistema operativo ingresado en el formulario de la pagina jsp            
            this.objSistemaOperativo.setSistemaOperativo(sistemaOperativo);//se setea la variable del objeto
            boolean soExiste = this.objSistemaOperativoDAO.validaSistemaOperativo(this.objSistemaOperativo);//se valida si el objeto ya existe

            //si el registro no existe en la tabla, se puede agregar el nuevo registro
            if (soExiste == false) {
                this.objSistemaOperativoDAO.agregar(this.objSistemaOperativo);//se envia el objeto SistemaOperativo al metodo agregar de DAO                
                request.setAttribute("mensaje", this.objSistemaOperativoDAO.getMensaje());//se envia el mensaje a la pagina jsp
                request.getRequestDispatcher("SistemaOperativoController?menu=sistemasOperativos&accion=Listar").forward(request, response);//se actualiza la tabla del mantenedor
            } else {
                request.setAttribute("mensaje", this.objSistemaOperativoDAO.getMensaje());//se envia el mensaje a la pagina jsp
                request.getRequestDispatcher("SistemaOperativoController?menu=sistemasOperativos&accion=Listar").forward(request, response);//se actualiza la tabla del mantenedor, sin cambios
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
        this.id = Integer.parseInt(request.getParameter("id"));//se recoge el id seleccionado con el boton Editar en la tabla
        this.objSistemaOperativo = this.objSistemaOperativoDAO.seleccionarPorId(this.id);//el objeto SistemaOperativo recoge el valor obtenido por el seleccionarPorId del objeto DAO        
        request.setAttribute("sistemaOperativo", this.objSistemaOperativo);//se envian los datos al formulario que agrega nuevos registros
        request.getRequestDispatcher("SistemaOperativoController?menu=sistemasOperativos&accion=Listar").forward(request, response);//se actualiza la tabla del mantenedor
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
            String sistemaOperativo = request.getParameter("txtSistemaOperativo");//se captura el sistema operativo ingresado en el formulario de la pagina jsp            
            this.objSistemaOperativo.setSistemaOperativo(sistemaOperativo);//se setea la variable del objeto
            boolean soExiste = this.objSistemaOperativoDAO.validaSistemaOperativo(this.objSistemaOperativo);//se valida si el objeto ya existe

            //si el registro no existe en la tabla, se puede agregar el nuevo registro
            if (soExiste == false) {
                this.objSistemaOperativoDAO.actualizar(this.objSistemaOperativo);//se envia el objeto SistemaOperativo al metodo actualizar de DAO                
                request.setAttribute("mensaje", this.objSistemaOperativoDAO.getMensaje());//se envia el mensaje a la pagina jsp
                request.getRequestDispatcher("SistemaOperativoController?menu=sistemasOperativos&accion=Listar").forward(request, response);//se actualiza la tabla del mantenedor
            } else {
                request.setAttribute("mensaje", this.objSistemaOperativoDAO.getMensaje());//se envia el mensaje a la pagina jsp
                request.getRequestDispatcher("SistemaOperativoController?menu=sistemasOperativos&accion=Listar").forward(request, response);//se actualiza la tabla del mantenedor, sin cambios
            }
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
        this.objSistemaOperativoDAO.eliminar(this.id);//se elimina el registro utilizando el metodo eliminar del objeto DAO       
        request.setAttribute("mensaje", this.objSistemaOperativoDAO.getMensaje());//se envia el mensaje a la pagina jsp 
        request.getRequestDispatcher("SistemaOperativoController?menu=sistemasOperativos&accion=Listar").forward(request, response);//se actualiza la tabla del mantenedor
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
