package controllers.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Custmer;
import utils.DBUtil;
import utils.EncryptUtil;


@WebServlet("/custmer/login")
public class LoginCustmerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public LoginCustmerServlet() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("hasError", false);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/custmer_login.jsp");
        rd.forward(request, response);
    }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean check_result = false;

        String name = request.getParameter("name");
        String plain_pass= request.getParameter("tell_num");

        Custmer c = null;

        if(name != null && !name.equals("") && plain_pass != null && !plain_pass.equals("")) {
            EntityManager em = DBUtil.createEntityManager();

            String tell_num = EncryptUtil.getPasswordEncrypt(
                    plain_pass,
                    (String)this.getServletContext().getAttribute("pepper")
                    );


            try {
                c = em.createNamedQuery("checkLoginNameAndTell_num", Custmer.class)
                      .setParameter("name", name)
                      .setParameter("tell_num", tell_num)
                      .getSingleResult();
            } catch(NoResultException ex) {}

            em.close();

            if(c != null) {
                check_result = true;
            }
        }

        if(!check_result) {

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true);
            request.setAttribute("name", name);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/custmer_login.jsp");
            rd.forward(request, response);
        } else {

            request.getSession().setAttribute("login_custmer", c);

            request.getSession().setAttribute("flush", "ログインしました。");

            response.sendRedirect(request.getContextPath() + "/custemr/calender");
        }
   }
}

