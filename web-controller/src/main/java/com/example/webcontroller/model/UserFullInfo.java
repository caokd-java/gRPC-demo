package com.example.webcontroller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFullInfo {
  private int userId;
  private String name;
  private int age;
  private String userAddress;
  private int companyId;
  private String companyName;
  private String companyAddress;
}
