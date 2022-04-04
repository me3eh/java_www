package com.example.filtry.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        System.out.println(rememberMe);
        if(username.isEmpty())
            username = "Empty";
        if( password.isEmpty() )
            password = "Empty";

        Cookie cookie=new Cookie("login", username);
        if( rememberMe == null )
            cookie.setMaxAge(10000*60);//okres ważności ciasteczka w s,
        else
            cookie.setMaxAge(-1);

        response.addCookie(cookie);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
