package edu.keepeasy.testcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Map<String, Object> model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            switch (statusCode) {
                case 400 -> {
                    model.put("code", "400");
                    model.put("message", "\"Плохой запрос\". Этот ответ означает, что сервер не понимает запрос из-за неверного синтаксиса. ");
                }
                case 500 -> {
                    model.put("code", "500");
                    model.put("message", "\"Не найден\". Сервер не может найти запрашиваемый ресурс. Код этого ответа, наверно, самый известный из-за частоты его появления в вебе. ");
                }
                case 404 -> {
                    model.put("code", "404");
                    model.put("message", "\"Внутренняя ошибка сервера\". Сервер столкнулся с ситуацией, которую он не знает как обработать.");
                }
                default -> model.put("message", "Другие ошибки");
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
