package controllers.custmer;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Custmer;
import utils.DBUtil;


@WebServlet("/custmer/show")
public class CustmerShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public CustmerShowServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Custmer c = em.find(Custmer.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("custmer", c);
        request.setAttribute("_token",request.getSession().getId());
        request.getSession().setAttribute("custmer_id",c.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/custmer/show.jsp");
        rd.forward(request, response);
    }

}
