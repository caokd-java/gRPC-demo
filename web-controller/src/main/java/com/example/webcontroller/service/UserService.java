package com.example.webcontroller.service;

import com.example.protobuf.user.UserFullInfoResponse;
import com.example.protobuf.user.UserRequest;
import com.example.protobuf.user.UserResponse;
import com.example.protobuf.user.UserServiceGrpc;
import com.example.webcontroller.model.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  ManagedChannel channelUser = ManagedChannelBuilder.forAddress("localhost", 1806).usePlaintext().build();
  UserServiceGrpc.UserServiceBlockingStub stubUser = UserServiceGrpc.newBlockingStub(channelUser);

  public UserResponse addUser(User user) {

    UserRequest userRequest = UserRequest.newBuilder()
        .setName(user.getName())
        .setAge(user.getAge())
        .setAddress(user.getAddress())
        .setCompanyId(user.getCompanyId())
        .build();

    UserResponse userResponse = stubUser.addUser(userRequest);

    return userResponse;
  }

  public UserResponse getUser(int userId) {

    UserRequest userRequest = UserRequest.newBuilder()
        .setId(userId)
        .build();

    UserResponse userResponse = stubUser.getUser(userRequest);

    return userResponse;
  }

  public UserFullInfoResponse getUserFullInfo(int userId) {

    UserRequest userRequest = UserRequest.newBuilder()
        .setId(userId)
        .build();

    UserFullInfoResponse userResponse = stubUser.getUserFullInfo(userRequest);

    return userResponse;
  }
}
