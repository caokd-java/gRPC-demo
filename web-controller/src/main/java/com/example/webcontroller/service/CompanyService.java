package com.example.webcontroller.service;

import com.example.protobuf.company.CompanyRequest;
import com.example.protobuf.company.CompanyResponse;
import com.example.protobuf.company.CompanyServiceGrpc;
import com.example.webcontroller.model.Company;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
  ManagedChannel channelCompany = ManagedChannelBuilder.forAddress("localhost", 1807).usePlaintext().build();
  CompanyServiceGrpc.CompanyServiceBlockingStub stubCompany = CompanyServiceGrpc.newBlockingStub(channelCompany);

  public CompanyResponse addCompany(Company company) {

    CompanyRequest companyRequest = CompanyRequest.newBuilder()
        .setName(company.getName())
        .setAddress(company.getAddress())
        .build();

    CompanyResponse companyResponse = stubCompany.addCompany(companyRequest);

    return companyResponse;
  }

  public CompanyResponse getCompany(int companyId) {

    CompanyRequest userRequest = CompanyRequest.newBuilder()
        .setId(companyId)
        .build();

    CompanyResponse userResponse = stubCompany.getCompany(userRequest);

    return userResponse;
  }
}
