package com.ys.eportal.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by rob on 4/5/15.
 */
@Controller
public class ExceptionHandlerController {
/*
        // @RequestHandler methods
        ...

        // Exception handling methods

        // Convert a predefined exception to an HTTP Status code
        @ResponseStatus(value=HttpStatus.CONFLICT, reason="Data integrity violation")  // 409
        @ExceptionHandler(DataIntegrityViolationException.class)
        public void conflict() {
            // Nothing to do
        }

        // Specify the name of a specific view that will be used to display the error:
        @ExceptionHandler({SQLException.class,DataAccessException.class})
        public String databaseError() {
            // Nothing to do.  Returns the logical view name of an error page, passed to
            // the view-resolver(s) in usual way.
            // Note that the exception is _not_ available to this view (it is not added to
            // the model) but see "Extending ExceptionHandlerExceptionResolver" below.
            return "databaseError";
        }

        // Total control - setup a model and return the view name yourself. Or consider
        // subclassing ExceptionHandlerExceptionResolver (see below).
        @ExceptionHandler(Exception.class)
        public ModelAndView handleError(HttpServletRequest req, Exception exception) {
            logger.error("Request: " + req.getRequestURL() + " raised " + exception);

            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", exception);
            mav.addObject("url", req.getRequestURL());
            mav.setViewName("error");
            return mav;
        }
        */

}
