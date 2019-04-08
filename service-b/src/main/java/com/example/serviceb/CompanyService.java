package com.example.serviceb;

import com.example.protobuf.company.CompanyRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
  List<Company> companyList = new ArrayList<>();
  int companyId = 0;

  public Company addCompany(CompanyRequest companyRequest) {
    Company company = new Company();

    company.setId(++companyId);
    company.setName(companyRequest.getName());
    company.setAddress(companyRequest.getAddress());

    this.companyList.add(company);

    return company;
  }

  public Company getCompany(int userId) {
    List<Company> users = this.companyList.stream().filter(item -> userId == item.getId()).collect(Collectors.toList());

    return users.get(0);
  }

  public List<Company> getCompanies() {
    return this.companyList;
  }
}
