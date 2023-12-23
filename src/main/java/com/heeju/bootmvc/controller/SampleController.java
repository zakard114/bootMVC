package com.heeju.bootmvc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public ModelAndView hello() {
        log.info("hello................");
        String msg = "HELLO WORLD";

        return new ModelAndView("hello", "msg", msg);
    }

}
