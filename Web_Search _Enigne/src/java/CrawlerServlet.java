

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spider.Spider;

/**
 *
 * @author kush
 */
@WebServlet(name = "CrawlerServlet", urlPatterns = {"/CrawlerServlet"})
public class CrawlerServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Crawling Result</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet CrawlerServlet at " + request.getContextPath() + "</h1> <br>");
            
            String log;
            int max;
            
            if(request.getParameter("maxName").equals(""))
                max = 10;
            else
                max = Integer.parseInt(request.getParameter("maxName"));
            
            if(request.getParameter("wordName").equals(""))
                log = new Spider().crawlAndSearch(request.getParameter("seedName"), null, max);
            else
                log = new Spider().crawlAndSearch(request.getParameter("seedName"), request.getParameter("wordName"), max);
            
            out.println("<h1>Crawling completed for triggered seed: " + request.getParameter("seedName") + "</h1>");
            out.println("Completion log: " + log);
            out.println("</body>");
            out.println("</html>");
        } 
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
