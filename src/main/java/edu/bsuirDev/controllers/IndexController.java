package edu.bsuirDev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * the first controller
 * */

@Controller
public class IndexController {
    @RequestMapping("/")
    @ResponseBody
    String hello()
    {
        return "Hello World!";
    }
}
