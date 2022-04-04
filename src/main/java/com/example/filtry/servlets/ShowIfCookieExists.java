package com.example.filtry.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "index", value = "/showIfCookieExists")
public class ShowIfCookieExists extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies=request.getCookies();
        boolean got = false;
        HttpSession session = request.getSession();
        for(int i=0;i<cookies.length;i++){
            Cookie cookie=cookies[i];
            System.out.println(cookie.getName() );
            if(cookie.getName().equals("login")){
                String login=cookie.getValue();
                System.out.println(login);
                session.setAttribute("loginCookie", login);
                got = true;
                break;
            }
        }
        if( !got )
            session.setAttribute("loginCookie", "Brak ciasteczka");
        request.getRequestDispatcher("forgetCookie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
