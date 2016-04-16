package pl.mroziqella.exception;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kamil on 09/04/2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")  // 404
@ControllerAdvice
public class ImageNotFound extends RuntimeException {

    @ExceptionHandler(ImageNotFound.class)
    public ModelAndView error(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error","Brak transmisji");
        modelAndView.setViewName("errorViews/errorPage");
        return modelAndView;
    }

}