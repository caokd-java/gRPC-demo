package com.example.servicea;

import com.example.protobuf.company.CompanyRequest;
import com.example.protobuf.company.CompanyResponse;
import com.example.protobuf.company.CompanyServiceGrpc;
import com.example.protobuf.user.UserFullInfoResponse;
import com.example.protobuf.user.UserRequest;
import com.example.protobuf.user.UserResponse;
import com.example.protobuf.user.UserResponses;
import com.example.protobuf.user.UserServiceGrpc.UserServiceImplBase;
import com.example.protobuf.user.gRPCUser;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class gRPCUserService extends UserServiceImplBase {

  ManagedChannel channelCompany = ManagedChannelBuilder.forAddress("localhost", 1807).usePlaintext().build();
  CompanyServiceGrpc.CompanyServiceBlockingStub stubCompany = CompanyServiceGrpc.newBlockingStub(channelCompany);

  final
  UserService userService;

  @Autowired
  public gRPCUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void addUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {

    log.info("User service received request: {}", request);

    User user = this.userService.addUser(request);

    gRPCUser gUser = this.convertUser(user);

    UserResponse userResponse = UserResponse.newBuilder().setUser(gUser).build();

    log.info("User service response: {}", userResponse);

    responseObserver.onNext(userResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {

    log.info("User service received request: {}", request);

    User user = this.userService.getUser(request.getId());

    gRPCUser gUser = this.convertUser(user);

    UserResponse userResponse = UserResponse.newBuilder().setUser(gUser).build();

    responseObserver.onNext(userResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void getUsers(UserRequest request, StreamObserver<UserResponses> responseObserver) {
    super.getUsers(request, responseObserver);
  }

  private gRPCUser convertUser(User user) {
    return gRPCUser.newBuilder()
        .setId(user.getId())
        .setName(user.getName())
        .setAge(user.getAge())
        .setAddress(user.getAddress())
        .setCompanyId(user.getCompanyId())
        .build();
  }

  @Override
  public void getUserFullInfo(UserRequest request, StreamObserver<UserFullInfoResponse> responseObserver) {
    log.info("User service received request: {}", request);

    User user = this.userService.getUser(request.getId());

    //from User service call Company service
    // 1. create CompanyRequest
    CompanyRequest companyRequest = CompanyRequest.newBuilder().setId(user.getCompanyId()).build();

    // 2. create CompanyResponse and call Company service
    CompanyResponse companyResponse = stubCompany.getCompany(companyRequest);

    UserFullInfoResponse userCompanyResponse = UserFullInfoResponse.newBuilder()
        .setUserId(user.getId())
        .setName(user.getName())
        .setAge(user.getAge())
        .setUserAddress(user.getAddress())
        .setCompanyId(companyResponse.getCompany().getId())
        .setCompanyName(companyResponse.getCompany().getName())
        .setCompanyAddress(companyResponse.getCompany().getAddress())
        .build();

    responseObserver.onNext(userCompanyResponse);
    responseObserver.onCompleted();
  }
}
