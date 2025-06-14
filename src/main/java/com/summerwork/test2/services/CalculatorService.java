package com.summerwork.test2.services;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public int getAdd(int x, int y) {
        return x + y;
    }
    public int getSub(int x, int y) {
        return x - y;
    }
    public String checkOddOrEven(){
        if(x % 2 == 0){
            return x + " Is Even" ;
        } else{
            return x + " Is Odd" ;
        }
    }
}




