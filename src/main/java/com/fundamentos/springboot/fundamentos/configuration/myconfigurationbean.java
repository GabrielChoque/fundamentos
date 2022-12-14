package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class myconfigurationbean {
    @Bean
    public mybean beanOperation() {

        return new mybean2implement();
    }
    @Bean
    public MyOperation beanOperationOperation(){

        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation){

        return new MyBeanWithDependencyImplement(myOperation);
    }
}
