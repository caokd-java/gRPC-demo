package com.example.serviceb;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class ServiceBApplication {

  public static void main(String[] args) throws Exception{
    SpringApplication.run(ServiceBApplication.class, args);

    CompanyService companyService = new CompanyService();

    Server server = ServerBuilder.forPort(1807).addService(new gRPCCompanyService(companyService)).build();

    server.start();
    server.awaitTermination();

//    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 1806).usePlaintext().build();
//
//    ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(channel);
//
//    List<String> hobbies = new ArrayList<>();
//    hobbies.add("Hobie 1");
//    hobbies.add("Hobie 2");
//    hobbies.add("Hobie 3");
//
//    Map<String, String> bagOfTricks = new HashMap<>();
//    bagOfTricks.put("1", "Trick 1");
//    bagOfTricks.put("2", "Trick 2");
//    bagOfTricks.put("3", "Trick 3");
//
//    ChatMessageRequest chatMessageRequest = ChatMessageRequest.newBuilder().setName("CaoKD").setMessage("Chao CaoKD")
//        .addAllHobbies(hobbies).putAllBagOfTricks(bagOfTricks).setSentiment(Sentiment.HAPPY).build();
//
//    log.info("Service B sent request: {}", chatMessageRequest);
//
//    ChatMessageResponse chatMessageResponse = stub.sendMessage(chatMessageRequest);
//
//    log.info("Service B received response: {}", chatMessageResponse);
//
//    channel.shutdown();
  }

}
