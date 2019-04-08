package com.example.servicea;

import com.example.protobuf.user.UserRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  List<User> userList = new ArrayList<>();
  int userId = 0;

  public User addUser(UserRequest userRequest) {
    User user = new User();

    user.setId(++userId);
    user.setName(userRequest.getName());
    user.setAge(userRequest.getAge());
    user.setAddress(userRequest.getAddress());
    user.setCompanyId(userRequest.getCompanyId());

    this.userList.add(user);

    return user;
  }

  public User getUser(int userId) {
    List<User> users = this.userList.stream().filter(item -> userId == item.getId()).collect(Collectors.toList());

    return users.get(0);
  }

  public List<User> getUsers() {
    return this.userList;
  }
}
