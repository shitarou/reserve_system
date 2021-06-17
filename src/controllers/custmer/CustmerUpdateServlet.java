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
import utils.EncryptUtil;


@WebServlet("/custmer/update")
public class CustmerUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public CustmerUpdateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Custmer c = em.find(Custmer.class, (Integer)(request.getSession().getAttribute("custmer_id")));


            Boolean nameDuplicateCheckFlag = true;
            if(c.getName().equals(request.getParameter("name"))) {
                nameDuplicateCheckFlag = false;
            } else {
                c.setName(request.getParameter("name"));
            }


            Boolean tell_numCheckFlag = true;
            String tell_num = request.getParameter("tell_num");
            if(tell_num == null || tell_num.equals("")) {
                tell_numCheckFlag = false;
            } else {
                c.setTell_num(
                        EncryptUtil.getPasswordEncrypt(
                                tell_num,
                                (String)this.getServletContext().getAttribute("pepper")
                                )
                        );
            }

            c.setName(request.getParameter("name"));
            c.setReserve_day(new Timestamp(System.currentTimeMillis()));


            List<String> errors = CustmerValidator.validate(c, nameDuplicateCheckFlag, tell_numCheckFlag);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("custmer", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/custmer/show.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("custmer_id");

                response.sendRedirect(request.getContextPath() + "/custmer/show");
            }
        }
    }

}
