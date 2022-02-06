package com.example.demo.controller;

import com.example.demo.core.RGBAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WebController {

    @Autowired
    RGBAnalyzer rgbAnalyzer;

    @RequestMapping("/hello_world")
    public String getResponse(){
        return "Hello World";
    }

    @GetMapping("/rgb")
    public String image(@RequestParam(value = "image") String image) throws IOException {
        return rgbAnalyzer.core(image);
    }
}
