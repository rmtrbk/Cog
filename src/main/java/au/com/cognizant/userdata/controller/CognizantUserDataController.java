package au.com.cognizant.userdata.controller;

import au.com.cognizant.userdata.service.RequestProcessorServiceImpl;
import java.io.IOException;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is the front controller of client back-end. All request routing 
 * and responses are managed in this class.
 * 
 * @author theja
 */
@PersistenceContext(name = "persistence/LogicalName", 
        unitName = "cognizantPU")
public class CognizantUserDataController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. 
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestProcessorServiceImpl requestProcessorService = 
                RequestProcessorServiceImpl.getInstance();

        String servletPath = request.getServletPath();
        String jsonString = null;

        if ("/Find".equalsIgnoreCase(servletPath)) {
            jsonString = requestProcessorService.processFind(request);

        } else if ("/Create".equalsIgnoreCase(servletPath)) {
            jsonString = requestProcessorService.processCreate(request);

        } else if ("/Retrieve".equalsIgnoreCase(servletPath)) {
            jsonString = requestProcessorService.processRetrieve(request);

        } else if ("/Update".equalsIgnoreCase(servletPath)) {
            jsonString = requestProcessorService.processUpdate(request);

        } else if ("/Delete".equalsIgnoreCase(servletPath)) {
            jsonString = requestProcessorService.processDelete(request);

        }
        
        response.setContentType("application/json");
        response.getWriter().write(jsonString);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
