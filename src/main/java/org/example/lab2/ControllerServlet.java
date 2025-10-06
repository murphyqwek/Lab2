package org.example.lab2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab2.utils.Validation;
import org.example.lab2.utils.ValidationResult;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Validation validation = new Validation(req.getParameter("xvalue"), req.getParameter("yvalue"), req.getParameter("rvalue"));

        ValidationResult result = validation.validateForAllNums();

        if(!result.isValid()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, result.errorMsg());
            return;
        }

        req.setAttribute("userValueBean", validation.getUserValueBean());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/areaChecker");
        dispatcher.forward(req, res);
    }
}
