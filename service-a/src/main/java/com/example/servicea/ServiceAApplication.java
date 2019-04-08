package com.example.servicea;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceAApplication {

  public static void main(String[] args) throws IOException, InterruptedException {
    SpringApplication.run(ServiceAApplication.class, args);

    UserService userService = new UserService();

    Server server = ServerBuilder.forPort(1806).addService(new gRPCUserService(userService)).build();

    server.start();
    server.awaitTermination();
  }

}
