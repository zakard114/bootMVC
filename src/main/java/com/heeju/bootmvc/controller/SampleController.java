package com.heeju.bootmvc.controller;

import lombok.Data;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public ModelAndView hello() {
        log.info("hello................");
        String msg = "HELLO WORLD";

        return new ModelAndView("hello", "msg", msg);
    }

    @GetMapping("/ex/ex1")
    public ModelAndView ex1(){

        List<String> list = Arrays.asList("AAA", "BBB", "CCC", "DDD");

        return new ModelAndView("ex/ex1", "list", list);
    }

    @Getter
    class SampleDTO {
        private String p1, p2, p3;
    }

    @GetMapping("/ex/ex2")
    public void ex2(Model model){
        log.info("ex/ex2...............");

        List<String> strList = IntStream.range(1,10)
                .mapToObj(i->"Data"+i)
                .collect(Collectors.toList());

        model.addAttribute("list", strList);

        Map<String, String> map = new HashMap<>();
        map.put("A", "AAAA");
        map.put("B", "BBBB");

        model.addAttribute("map", map);

        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.p1 = "Value -- p1";
        sampleDTO.p2 = "Value -- p2";
        sampleDTO.p3 = "Value -- p3";

        model.addAttribute("dto", sampleDTO);
    }

    @GetMapping("/ex/ex3")
    public void ex3(Model model){
        model.addAttribute("arr", new String[]{"AAA","BBB","CCC"});
    }
}
