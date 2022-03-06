package com.example.first_one;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PrintServlet", value = "/printServlet")
public class PrintServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = this.getServletContext();

        String htmlResponse = "<html>";
        htmlResponse += "<h2>Your username is: " + context.getAttribute("username") + "<br/>";
        htmlResponse += "Your password is: " + context.getAttribute("password") + "</h2>";
        htmlResponse += "</html>";
        PrintWriter writer = response.getWriter();

        writer.println(htmlResponse);
    }
}
