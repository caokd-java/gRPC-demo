package com.example.webcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
//@ComponentScans(@ComponentScan("com.example.servicea"))
public class WebControllerApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebControllerApplication.class, args);
  }

}
