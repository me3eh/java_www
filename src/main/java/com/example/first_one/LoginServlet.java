package com.example.first_one;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.isEmpty())
            username = "Empty";
        if( password.isEmpty() )
            password = "Empty";
        ServletContext context = this.getServletContext();

        context.setAttribute("username", username);
        context.setAttribute("password", password);

        System.out.println("username: " + username);
        System.out.println("password: " + password);
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/printServlet");
        dispatcher.forward(request, response);
    }
}
