package com.fundamentos.springboot.fundamentos.bean;

import org.springframework.stereotype.Component;


public class mybeanimplement implements mybean {

    @Override
    public void print() {
        System.out.println("hola desde mi implementacion del bean");
    }
}
