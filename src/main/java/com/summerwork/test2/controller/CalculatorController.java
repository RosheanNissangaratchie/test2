package com.summerwork.test2.controller;

import com.summerwork.test2.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        int result = calculatorService.getAdd(a, b);
        return result;
    }

    @GetMapping("/sub")
    public int subtract(@RequestParam int a, @RequestParam int b) {
        int result = calculatorService.getSub(a, b);
        return result;
    }

    @GetMapping("/check")
    public String check(@RequestParam int x) {
        return calculatorService.checkOddOrEven(x);

    }
}