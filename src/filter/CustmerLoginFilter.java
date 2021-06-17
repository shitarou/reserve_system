package filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Custmer;
import models.Employee;
@WebFilter("/hogec")
public class CustmerLoginFilter implements Filter {
    public CustmerLoginFilter() {
    }
    public void destroy() {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String context_path = ((HttpServletRequest) request).getContextPath();
        String servlet_path = ((HttpServletRequest) request).getServletPath();
        if (!servlet_path.matches("/css.*")) {
            HttpSession session = ((HttpServletRequest) request).getSession();
            Custmer c = (Custmer) session.getAttribute("login_custmer");
            Employee e = (Employee) session.getAttribute("login_employee");
            if (!servlet_path.equals("/login")
                    && !servlet_path.equals("/custmer/login")) {
                if (c == null) {
                    if (servlet_path.matches("/custmer.*")) {
                        if (e.getAdmin_flag() == 1) {
                            ((HttpServletResponse) response).sendRedirect(context_path + "/");
                            return;
                        } else {
                            ((HttpServletResponse) response).sendRedirect(context_path + "/custmer/login");
                            return;
                        }
                    } else {
                        ((HttpServletResponse) response).sendRedirect(context_path + "/login");
                        return;
                    }
                } else {
                    if (c != null) {
                        ((HttpServletResponse) response).sendRedirect(context_path + "/");
                        return;
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
    public void init(FilterConfig fConfig) throws ServletException {
    }
}