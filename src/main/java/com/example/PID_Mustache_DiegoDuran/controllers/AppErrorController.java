package com.example.PID_Mustache_DiegoDuran.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        model.addAttribute("errorTitle", status == null ? "Error" : "Error " + status);
        model.addAttribute("errorMessage", message == null || message.toString().isBlank()
                ? "Ha ocurrido un problema al procesar la peticion."
                : message.toString());
        return "error";
    }
}
