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


@WebFilter("/custmer/login")
public class CustmerLoginFilter implements Filter {




        public CustmerLoginFilter() {
        }


        public void destroy() {
        }



        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            String context_path = ((HttpServletRequest)request).getContextPath();
            String servlet_path = ((HttpServletRequest)request).getServletPath();

            if(!servlet_path.matches("/css.*")) {
                HttpSession session = ((HttpServletRequest)request).getSession();


                Custmer c = (Custmer)session.getAttribute("login_custmer");

                if(!servlet_path.equals("/login")) {

                    if(c == null) {
                        ((HttpServletResponse)response).sendRedirect(context_path + "/login");
                        return;
                    }


                    if(servlet_path.matches("/custmer.*") ) {
                        ((HttpServletResponse)response).sendRedirect(context_path + "/custmer/login");
                        return;
                    }
                } else {
                    if(c != null) {
                        ((HttpServletResponse)response).sendRedirect(context_path + "/custmer/login");
                        return;
                    }
                }
            }

            chain.doFilter(request, response);
        }


        public void init(FilterConfig fConfig) throws ServletException {
        }

    }