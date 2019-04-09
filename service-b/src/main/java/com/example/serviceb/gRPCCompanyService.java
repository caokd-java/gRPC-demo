package com.example.serviceb;

import com.example.protobuf.company.CompanyRequest;
import com.example.protobuf.company.CompanyResponse;
import com.example.protobuf.company.CompanyResponses;
import com.example.protobuf.company.CompanyServiceGrpc.CompanyServiceImplBase;
import com.example.protobuf.company.gRPCCompany;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
@GrpcService
public class gRPCCompanyService extends CompanyServiceImplBase {

  @Autowired
  CompanyService companyService;

  @Override
  public void addCompany(CompanyRequest request, StreamObserver<CompanyResponse> responseObserver) {
    log.info("User service received request: {}", request);

    Company company = this.companyService.addCompany(request);

    gRPCCompany gCompany = this.convertCompany(company);

    CompanyResponse companyResponse = CompanyResponse.newBuilder().setCompany(gCompany).build();

    log.info("Company service response: {}", companyResponse);

    responseObserver.onNext(companyResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void getCompany(CompanyRequest request, StreamObserver<CompanyResponse> responseObserver) {
    log.info("Company service received request: {}", request);

    Company company = this.companyService.getCompany(request.getId());

    gRPCCompany gUser = this.convertCompany(company);

    CompanyResponse companyResponse = CompanyResponse.newBuilder().setCompany(gUser).build();

    responseObserver.onNext(companyResponse);
    responseObserver.onCompleted();
  }

  @Override
  public void getCompanies(CompanyRequest request, StreamObserver<CompanyResponses> responseObserver) {
    super.getCompanies(request, responseObserver);
  }

  private gRPCCompany convertCompany(Company company) {
    return gRPCCompany.newBuilder()
        .setId(company.getId())
        .setName(company.getName())
        .setAddress(company.getAddress())
        .build();
  }
}
