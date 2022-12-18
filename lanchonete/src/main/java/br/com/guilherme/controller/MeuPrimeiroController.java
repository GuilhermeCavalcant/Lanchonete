package br.com.guilherme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {
    @GetMapping("/oi")
    @ResponseBody
    public String hello() {
        return "Oi, tudo bem?";

    }
}