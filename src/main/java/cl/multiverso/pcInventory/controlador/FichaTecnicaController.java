package cl.multiverso.pcInventory.controlador;

import cl.multiverso.pcInventory.dao.EmpleadoDAO;
import cl.multiverso.pcInventory.dao.EquipoDAO;
import cl.multiverso.pcInventory.dao.EstadoOperativoDAO;
import cl.multiverso.pcInventory.dao.MarcaDAO;
import cl.multiverso.pcInventory.dao.SistemaOperativoDAO;
import cl.multiverso.pcInventory.dao.TipoDiscoDuroDAO;
import cl.multiverso.pcInventory.dao.TipoEquipoDAO;
import cl.multiverso.pcInventory.dao.TipoProcesadorDAO;
import cl.multiverso.pcInventory.dao.TipoRamDAO;
import cl.multiverso.pcInventory.dao.TipoUsuarioDAO;
import cl.multiverso.pcInventory.dao.UnidadDAO;
import cl.multiverso.pcInventory.modelo.Empleado;
import cl.multiverso.pcInventory.modelo.Equipo;
import cl.multiverso.pcInventory.modelo.EstadoOperativo;
import cl.multiverso.pcInventory.modelo.Marca;
import cl.multiverso.pcInventory.modelo.SistemaOperativo;
import cl.multiverso.pcInventory.modelo.TipoDiscoDuro;
import cl.multiverso.pcInventory.modelo.TipoEquipo;
import cl.multiverso.pcInventory.modelo.TipoProcesador;
import cl.multiverso.pcInventory.modelo.TipoRam;
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
 * Servlet FichaTecnicaController. 22/04/2021
 *
 * @author Carlos
 */
@WebServlet(name = "FichaTecnicaController", urlPatterns = {"/FichaTecnicaController"})
public class FichaTecnicaController extends HttpServlet {

    Equipo objEquipo = new Equipo();
    EquipoDAO objEquipoDAO = new EquipoDAO();
    EstadoOperativo objEstadoOperativo = new EstadoOperativo();
    EstadoOperativoDAO objEstadoOperativoDAO = new EstadoOperativoDAO();
    TipoEquipo objTipoEquipo = new TipoEquipo();
    TipoEquipoDAO objTipoEquipoDAO = new TipoEquipoDAO();
    Marca objMarca = new Marca();
    MarcaDAO objMarcaDAO = new MarcaDAO();
    Empleado objEmpleado = new Empleado();
    EmpleadoDAO objEmpleadoDAO = new EmpleadoDAO();
    TipoProcesador objTipoProcesador = new TipoProcesador();
    TipoProcesadorDAO objTipoProcesadorDAO = new TipoProcesadorDAO();
    TipoRam objTipoRam = new TipoRam();
    TipoRamDAO objTipoRamDAO = new TipoRamDAO();
    TipoDiscoDuro objTipoDiscoDuro = new TipoDiscoDuro();
    TipoDiscoDuroDAO objTipoDiscoDuroDAO = new TipoDiscoDuroDAO();
    SistemaOperativo objSistemaOperativo = new SistemaOperativo();
    SistemaOperativoDAO objSistemaOperativoDAO = new SistemaOperativoDAO();
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

        if (menu.equals("fichaTecnica")) {
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
                default:
                    request.getRequestDispatcher("/WEB-INF/paginas/fichaTecnica.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/paginas/fichaTecnica.jsp").forward(request, response);
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
        //se setean las listas de empleados y tipos de usuario
        List listaEquipos = this.objEquipoDAO.listar();

        if (listaEquipos.isEmpty()) {
            //si la lista esta vacia se envia el mensaje a la pagina jsp
            request.setAttribute("mensaje", "No hay registros");
        } else {
            //se envian las listas al alcance de la pagina jsp
            request.setAttribute("listaEquipos", listaEquipos);
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
        //se setea y se envia las listas de tipos a la pagina agregarEquipo.jsp, en el alcance de request.
        request.setAttribute("listaEstadosOperacionales", this.objEstadoOperativoDAO.listar());
        request.setAttribute("listaTiposEquipo", this.objTipoEquipoDAO.listar());
        request.setAttribute("listaMarcas", this.objMarcaDAO.listar());
        request.setAttribute("listaEmpleados", this.objEmpleadoDAO.listar());
        request.setAttribute("listaTiposProcesador", this.objTipoProcesadorDAO.listar());
        request.setAttribute("listaTiposRam", this.objTipoRamDAO.listar());
        request.setAttribute("listaTiposDiscoDuro", this.objTipoDiscoDuroDAO.listar());
        request.setAttribute("listaSO", this.objSistemaOperativoDAO.listar());
        request.getRequestDispatcher("/WEB-INF/paginas/agregarEquipo.jsp").forward(request, response);
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
            //se capturan los datos ingresados en el formulario de la pagina agregarEquipo.jsp 
            int idTipoEquipo = Integer.parseInt(request.getParameter("cboTipoEquipo"));//1
            String numeroSerie = request.getParameter("txtNumeroSerie");//2
            int idMarca = Integer.parseInt(request.getParameter("cboMarca"));//3
            String hostname = request.getParameter("txtHostname");//4
            int idProcesador = Integer.parseInt(request.getParameter("cboProcesador"));//5
            int velocidadProcesador = Integer.parseInt(request.getParameter("txtVelocidadProcesador"));//6
            int idTipoRam = Integer.parseInt(request.getParameter("cboRam"));//7
            int capacidadRam = Integer.parseInt(request.getParameter("txtCapacidadRam"));//8
            int idTipoDiscoDuro = Integer.parseInt(request.getParameter("cboDiscoDuro"));//9
            int capacidadDiscoDuro = Integer.parseInt(request.getParameter("txtCapacidadDiscoDuro"));//10
            int idEstadoOperacional = Integer.parseInt(request.getParameter("cboEstadoOperacional"));//11
            int idSistemaOperativo = Integer.parseInt(request.getParameter("cboSistemaOperativo"));//12
            int idEmpleado = Integer.parseInt(request.getParameter("cboEmpleado"));//13

            //se setean la variables del objeto
            this.objTipoEquipo.setIdTipoEquipo(idTipoEquipo);
            this.objEquipo.setObjTipoEquipo(objTipoEquipo);
            this.objEquipo.setNumeroSerie(numeroSerie);
            this.objMarca.setIdMarca(idMarca);
            this.objEquipo.setObjMarca(objMarca);
            this.objEquipo.setHostname(hostname);
            this.objTipoProcesador.setIdTipoProcesador(idProcesador);
            this.objEquipo.setObjTipoProcesador(objTipoProcesador);
            this.objEquipo.setVelocidadProcesador(velocidadProcesador);
            this.objTipoRam.setIdTipoRam(idTipoRam);
            this.objEquipo.setObjTipoRam(objTipoRam);
            this.objEquipo.setCapacidadRam(capacidadRam);
            this.objTipoDiscoDuro.setIdTipoDiscoDuro(idTipoDiscoDuro);
            this.objEquipo.setObjTipoDiscoDuro(objTipoDiscoDuro);
            this.objEquipo.setCapacidadDiscoDuro(capacidadDiscoDuro);
            this.objEstadoOperativo.setIdEstadoOperativo(idEstadoOperacional);
            this.objEquipo.setObjEstadoOperativo(objEstadoOperativo);
            this.objSistemaOperativo.setIdSistemaOperativo(idSistemaOperativo);
            this.objEquipo.setObjSistemaOperativo(objSistemaOperativo);
            this.objEmpleado.setIdEmpleado(idEmpleado);
            this.objEquipo.setObjEmpleado(objEmpleado);

            //se verifica si el empleado ya existe en la tabla tipo empleado
            boolean equipoExiste = this.objEquipoDAO.validaEquipo(objEquipo);

            //si el registro no existe en la tabla, se puede agregar el nuevo registro
            if (equipoExiste == false) {
                //se envia el objeto Equipo al metodo agregar de DAO
                System.out.println("EQUIPO === " + objEquipo.toString());

                this.objEquipoDAO.agregar(this.objEquipo);
                //se envia el mensaje a la pagina jsp
                request.setAttribute("mensaje", this.objEquipoDAO.getMensaje());
                //se actualiza la tabla del mantenedor
                request.getRequestDispatcher("FichaTecnicaController?menu=fichaTecnica&accion=Listar").forward(request, response);
            } else {
                //se genera un mensaje de error y se envia el mensaje a la pagina jsp 
                request.setAttribute("mensaje", this.objEquipoDAO.getMensaje());
                //se actualiza la tabla del mantenedor
                request.getRequestDispatcher("FichaTecnicaController?menu=fichaTecnica&accion=Listar").forward(request, response);
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
        //el objeto Equipo recoge el valor obtenido por el seleccionarPorId del objeto DAO 
        this.objEquipo = this.objEquipoDAO.seleccionarPorId(this.id);
        //se envian los datos al formulario que actualiza registros , en el alcance de request
        request.setAttribute("equipo", this.objEquipo);
        //se setean y se envian las listas de tipos a la pagina editarEmpleado.jsp, en el alcance de request.
        request.setAttribute("listaEstadosOperacionales", this.objEstadoOperativoDAO.listar());
        request.setAttribute("listaTiposEquipo", this.objTipoEquipoDAO.listar());
        request.setAttribute("listaMarcas", this.objMarcaDAO.listar());
        request.setAttribute("listaEmpleados", this.objEmpleadoDAO.listar());
        request.setAttribute("listaTiposProcesador", this.objTipoProcesadorDAO.listar());
        request.setAttribute("listaTiposRam", this.objTipoRamDAO.listar());
        request.setAttribute("listaTiposDiscoDuro", this.objTipoDiscoDuroDAO.listar());
        request.setAttribute("listaSO", this.objSistemaOperativoDAO.listar());
        request.getRequestDispatcher("/WEB-INF/paginas/editarEquipo.jsp").forward(request, response);
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
            //se capturan los datos ingresados en el formulario de la pagina editarEquipo.jsp
            int idEstadoOperacional = Integer.parseInt(request.getParameter("cboEstadoOperacional"));
            String hostname = request.getParameter("txtHostname");
            int idEmpleado = Integer.parseInt(request.getParameter("cboEmpleado"));
            int idProcesador = Integer.parseInt(request.getParameter("cboProcesador"));
            int velocidadProcesador = Integer.parseInt(request.getParameter("txtVelocidadProcesador"));
            int idTipoRam = Integer.parseInt(request.getParameter("cboRam"));
            int capacidadRam = Integer.parseInt(request.getParameter("txtCapacidadRam"));
            int idTipoDiscoDuro = Integer.parseInt(request.getParameter("cboDiscoDuro"));
            int capacidadDiscoDuro = Integer.parseInt(request.getParameter("txtCapacidadDiscoDuro"));
            int idSistemaOperativo = Integer.parseInt(request.getParameter("cboSistemaOperativo"));

            //se setean la variables del objeto
            this.objEstadoOperativo.setIdEstadoOperativo(idEstadoOperacional);
            this.objEquipo.setObjEstadoOperativo(this.objEstadoOperativo);
            this.objEquipo.setHostname(hostname);
            this.objEmpleado.setIdEmpleado(idEmpleado);
            this.objEquipo.setObjEmpleado(this.objEmpleado);
            this.objTipoProcesador.setIdTipoProcesador(idProcesador);
            this.objEquipo.setObjTipoProcesador(this.objTipoProcesador);
            this.objEquipo.setVelocidadProcesador(velocidadProcesador);
            this.objTipoRam.setIdTipoRam(idTipoRam);
            this.objEquipo.setObjTipoRam(this.objTipoRam);
            this.objEquipo.setCapacidadRam(capacidadRam);
            this.objTipoDiscoDuro.setIdTipoDiscoDuro(idTipoDiscoDuro);
            this.objEquipo.setObjTipoDiscoDuro(this.objTipoDiscoDuro);
            this.objEquipo.setCapacidadDiscoDuro(capacidadDiscoDuro);
            this.objSistemaOperativo.setIdSistemaOperativo(idSistemaOperativo);
            this.objEquipo.setObjSistemaOperativo(this.objSistemaOperativo);
            
            //se envia el objeto al metodo actualizar de DAO
            this.objEquipoDAO.actualizar(this.objEquipo);
            //se envia el mensaje a la pagina jsp
            request.setAttribute("mensaje", this.objEquipoDAO.getMensaje());
            //se actualiza la tabla del mantenedor
            request.getRequestDispatcher("FichaTecnicaController?menu=fichaTecnica&accion=Listar").forward(request, response);            
        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "-Error- No se puede procesar la operación " + ex.getMessage());//se envia el mensaje a la pagina jsp
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
        this.objEquipoDAO.eliminar(this.id);//se elimina el registro utilizando el metodo eliminar del objeto DAO       
        request.setAttribute("mensaje", this.objEquipoDAO.getMensaje());//se envia el mensaje a la pagina jsp 
        request.getRequestDispatcher("FichaTecnicaController?menu=fichaTecnica&accion=Listar").forward(request, response);//se actualiza la tabla del mantenedor
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
