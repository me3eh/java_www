package com.example.filtry.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "forget", value = "/forget")
public class Forget extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        System.out.println( cookies.length );
//przeglądanie tablicy z ciasteczkami
        boolean got = false;
        HttpSession session = request.getSession();
        for(int i=0;i<cookies.length;i++){
            Cookie cookie=cookies[i];

            System.out.println( cookie.getName() );

            System.out.println(cookie.getName() );
            if(cookie.getName().equals("login")){
                cookie.setMaxAge(0);
                response.addCookie( cookie );
                session.setAttribute("yep", "Brak ciasteczka");
                break;
            }
        }
        response.sendRedirect(request.getContextPath() + "/showIfCookieExists");
    }
}
