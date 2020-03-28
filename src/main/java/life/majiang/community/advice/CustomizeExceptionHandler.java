package life.majiang.community.advice;

import life.majiang.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e , Model model) {
        if (e instanceof CustomizeException){
            model.addAttribute("msg" , e.getMessage());
        }else {
            model.addAttribute("msg" , "服务异常，再试试？");
        }
        return new ModelAndView("error");
    }
}
