package com.ajackus.dlbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class ShutdownController {

    @Autowired
    private ApplicationContext context;

    /*
        * This method is used to shutdown the application context.
        * It will close the application context and stop the application.
        * The response will be empty in the postman, if you're checking on postman.
     */
    @PostMapping("/fullShutdown")
    public void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }
}