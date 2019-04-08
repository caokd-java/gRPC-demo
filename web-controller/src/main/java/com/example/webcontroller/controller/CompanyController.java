package com.example.webcontroller.controller;

import com.example.protobuf.company.CompanyResponse;
import com.example.protobuf.user.UserResponse;
import com.example.webcontroller.model.Company;
import com.example.webcontroller.model.User;
import com.example.webcontroller.service.CompanyService;
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
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @PostMapping(value = "/addCompany")
  private ResponseEntity<Company> addCompany(@RequestBody Company company) {
    CompanyResponse companyResponse = this.companyService.addCompany(company);

    Company c = convertUser(companyResponse);

    return new ResponseEntity<>(c, HttpStatus.OK);
  }

  @GetMapping(value = "/getCompany")
  private ResponseEntity<Company> getCompany(@RequestParam("companyId") int companyId) {
    CompanyResponse companyResponse = this.companyService.getCompany(companyId);

    Company c = convertUser(companyResponse);

    return new ResponseEntity<>(c, HttpStatus.OK);
  }

  private Company convertUser(CompanyResponse companyResponse) {
    Company c = new Company();
    c.setId(companyResponse.getCompany().getId());
    c.setName(companyResponse.getCompany().getName());
    c.setAddress(companyResponse.getCompany().getAddress());
    return c;
  }
}
