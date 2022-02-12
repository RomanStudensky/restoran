package ru.pnzgu.restauran.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import ru.pnzgu.restauran.exception.DocumentExportException;
import ru.pnzgu.restauran.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Configuration
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        ModelAndView mv = new ModelAndView();
        // Оцениваем разные типы исключений и делаем разные переходы вида
        if(ex instanceof NotFoundException || ex instanceof DocumentExportException){
            mv.setViewName("error");
        }
        mv.addObject("error", ex.toString());

        return mv;
    }
}