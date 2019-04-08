package com.example.webcontroller.controller;

import com.example.protobuf.user.UserFullInfoResponse;
import com.example.protobuf.user.UserResponse;
import com.example.webcontroller.model.User;
import com.example.webcontroller.model.UserFullInfo;
import com.example.webcontroller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/addUser")
  private ResponseEntity<User> addUser(@RequestBody User user) {
    UserResponse userResponse = this.userService.addUser(user);

    User u = convertUser(userResponse);

    return new ResponseEntity<>(u, HttpStatus.OK);
  }

  @GetMapping(value = "/getUser")
  private ResponseEntity<User> getUser(@RequestParam("userId") int userId) {
    UserResponse userResponse = this.userService.getUser(userId);

    User u = convertUser(userResponse);

    return new ResponseEntity<>(u, HttpStatus.OK);
  }

  @GetMapping(value = "/getUserFullInfo")
  private ResponseEntity<UserFullInfo> getUserFullInfo(@RequestParam("userId") int userId) {

    UserFullInfoResponse userCompany = this.userService.getUserFullInfo(userId);

    UserFullInfo userFullInfo = this.convertUserFullInfo(userCompany);

    return new ResponseEntity<>(userFullInfo, HttpStatus.OK);
  }

  private User convertUser(UserResponse userResponse) {
    User u = new User();

    u.setId(userResponse.getUser().getId());
    u.setName(userResponse.getUser().getName());
    u.setAge(userResponse.getUser().getAge());
    u.setAddress(userResponse.getUser().getAddress());
    u.setCompanyId(userResponse.getUser().getCompanyId());

    return u;
  }

  private UserFullInfo convertUserFullInfo(UserFullInfoResponse userFullInfoResponse) {
    UserFullInfo u = new UserFullInfo();

    u.setUserId(userFullInfoResponse.getUserId());
    u.setName(userFullInfoResponse.getName());
    u.setAge(userFullInfoResponse.getAge());
    u.setUserAddress(userFullInfoResponse.getUserAddress());
    u.setCompanyId(userFullInfoResponse.getCompanyId());
    u.setCompanyName(userFullInfoResponse.getCompanyName());
    u.setCompanyAddress(userFullInfoResponse.getCompanyAddress());

    return u;
  }
}
