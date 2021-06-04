package controllers.custmer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Custmer;


@WebServlet("/custmer/new")
public class CustmerNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public CustmerNewServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("custmer", new Custmer());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/custmer/new.jsp");
        rd.forward(request, response);
    }

}