package controllers.custmer;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Custmer;
import utils.DBUtil;

@WebServlet("/custmer/destroy")
public class CustmerDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public CustmerDestroyServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Custmer c = em.find(Custmer.class, (Integer) (request.getSession().getAttribute("custmer_id")));
            c.setDelete_flag(1);


            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "削除が完了しました");

            response.sendRedirect(request.getContextPath() + "/custmer/index");
        }
    }

}
