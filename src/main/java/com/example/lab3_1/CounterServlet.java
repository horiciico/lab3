package com.example.lab3_1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CounterServlet", value = "/CounterServlet")
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer counterGet = (Integer) session.getAttribute("CounterGetName");
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

        session.setAttribute("CounterGetName", counterGet);

        if (counterGet == 0) {
            session.setAttribute("warningGet", true);
        } else {
            session.removeAttribute("warningGet");
        }

        status(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer counterPost = (Integer) session.getAttribute("CounterPostName");
        if (counterPost == null) {
            counterPost = 0;
        }

        String incParam = request.getParameter("inc");
        String decParam = request.getParameter("dec");

        if (incParam != null) {
            try {
                int val = Integer.parseInt(incParam);
                counterPost += val;
            } catch (NumberFormatException e) {
                counterPost++;
            }
        }

        if (decParam != null) {
            try {
                int val = Integer.parseInt(decParam);
                counterPost -= val;
            } catch (NumberFormatException e) {
                counterPost--;
            }
        }

        session.setAttribute("CounterPostName", counterPost);

        if (counterPost == 0) {
            session.setAttribute("warningPost", true);
        } else {
            session.removeAttribute("warningPost");
        }

        status(request, response);
    }

    private void status(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.println("<!DOCTYPE html>");
        printWriter.println("<html>");
        printWriter.println("<head><title>Counter Servlet</title></head>");
        printWriter.println("<body>");
        printWriter.println("<h2>Welcome to Counter Servlet</h2>");

        HttpSession session = request.getSession();

        Integer counterGet = (Integer) session.getAttribute("CounterGetName");
        printWriter.println("<br> Counter for GET: " + (counterGet != null ? counterGet : 0));



        Boolean warningGet = (Boolean) session.getAttribute("warningGet");
        if (Boolean.TRUE.equals(warningGet)) {
            printWriter.println("<br><strong style='color:red;'>WARNING GET</strong>");
        }

        printWriter.println("<br>");

        Integer counterPost = (Integer) session.getAttribute("CounterPostName");
        printWriter.println("<br> Counter for POST: " + (counterPost != null ? counterPost : 0));


        Boolean warningPost = (Boolean) session.getAttribute("warningPost");
        if (Boolean.TRUE.equals(warningPost)) {
            printWriter.println("<br><strong style='color:red;'>WARNING POST</strong>");
        }

        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}