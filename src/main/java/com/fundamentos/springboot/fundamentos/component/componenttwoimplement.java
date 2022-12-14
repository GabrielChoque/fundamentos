package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class componenttwoimplement implements ComponentDependency{

    @Override
    public void saludar() {
        System.out.println("saludo gabo desde el dos");
    }
}
