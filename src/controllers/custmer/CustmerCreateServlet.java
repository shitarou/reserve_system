package controllers.custmer;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Custmer;
import models.validators.CustmerValidator;
import utils.DBUtil;

@WebServlet("/custmer/create")
public class CustmerCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public CustmerCreateServlet() {
        super();

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Custmer c = new Custmer();

            c.setName(request.getParameter("name"));
            c.setTell_num(request.getParameter("tell_num"));
            c.setPeoples(request.getParameter("peoples"));


            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            c.setReserve_day(currentTime);
            c.setVisit_time(currentTime);
            c.setExit_time(currentTime);


            List<String> errors = CustmerValidator.validate(c);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("cutmer", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/custmer/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();

                response.sendRedirect(request.getContextPath() + "/custmer/new");

            }
        }
    }

}
