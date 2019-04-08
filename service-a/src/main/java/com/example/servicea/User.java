package com.example.servicea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
  private int id;
  private String name;
  private int age;
  private String address;
  private int companyId;
}
