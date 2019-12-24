package com.xia.demo.imports;

import com.xia.demo.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xiafb
 * @date Created in 2019/12/16 16:53
 * description
 * modified By
 * version
 */
@Configuration
@Import(Color.class)
public class CarBeanConfig {

    @Bean
    public Car miniCar(){
        Car car = new Car();
        car.setName("汽车");
        car.setSize("小");
        return car;
    }
}
