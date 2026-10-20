package com.example.lab3_1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CounterServlet", value = "/CounterServlet")
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer counterGet = (Integer) request.getSession().getAttribute("CounterGetName");
        if (counterGet == null) {
            counterGet = 0;
        }

        String incParam = request.getParameter("inc");
        String decParam = request.getParameter("dec");

        if (incParam != null) {
            try {
                int val = Integer.parseInt(incParam);
                counterGet += val;
            } catch (NumberFormatException e) {
                counterGet++;
            }
        }


        if (decParam != null) {
            try {
                int val = Integer.parseInt(decParam);
                counterGet -= val;
            } catch (NumberFormatException e) {
                counterGet--;
            }
        }

        request.getSession().setAttribute("CounterGetName", counterGet);
        status(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer counterPost = (Integer) request.getSession().getAttribute("CounterPostName");
        if (counterPost == null) {
            counterPost = 0;
        }

        String incParam = request.getParameter("inc");
        String decParam = request.getParameter("dec");

        // incrementare post
        if (incParam != null) {
            try {
                int val = Integer.parseInt(incParam);
                counterPost += val;
            } catch (NumberFormatException e) {
                counterPost++;
            }
        }

        // decrementare post
        if (decParam != null) {
            try {
                int val = Integer.parseInt(decParam);
                counterPost -= val;
            } catch (NumberFormatException e) {
                counterPost--;
            }
        }

        request.getSession().setAttribute("CounterPostName", counterPost);
        status(request, response);
    }

    private void status(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.println("<!DOCTYPE html>");
        printWriter.println("<html>");
        printWriter.println("<head><title>Counter Servlet</title></head>");
        printWriter.println("<body>");
        printWriter.println("<h2>Welcome to Counter Servlet</h2>");

        Integer counterGet = (Integer) request.getSession().getAttribute("CounterGetName");
        printWriter.println("<br> Counter for GET: " + counterGet);

        Integer counterPost = (Integer) request.getSession().getAttribute("CounterPostName");
        printWriter.println("<br> Counter for POST: " + counterPost);

        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}