package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

import java.time.Clock;


@Component
public class componentimplement implements ComponentDependency {
    @Override
    public void saludar() {

        System.out.println("hola gabo");
    }
}
