package net.njema.customerdataservice.web;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import net.njema.customerdataservice.entities.Customer;
import net.njema.customerdataservice.mapper.CustomerMapper;
import net.njema.customerdataservice.repositories.CustomerRepository;
import net.njema.customerdataservice.stub.CustomerServiceGrpc;
import net.njema.customerdataservice.stub.CustomerServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomersResponse> responseObserver) {

        List<Customer> customerList=customerRepository.findAll();
        List<CustomerServiceOuterClass.Customer> grpcCustomers = customerList.stream()
                .map(customerMapper::fromCustomer).collect(Collectors.toList());
        CustomerServiceOuterClass.GetCustomersResponse customersResponse= CustomerServiceOuterClass.GetCustomersResponse.newBuilder()
                .addAllCustomers(grpcCustomers)
                .build();
        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetAllCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customerEntity=customerRepository.findById(request.getId()).get();
        CustomerServiceOuterClass.Customer customer = customerMapper.fromCustomer(customerEntity);
        CustomerServiceOuterClass.GetCustomerByIdResponse response= CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                .setCustomer(customer)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        CustomerServiceOuterClass.CustomerRequest customerGrpc = request.getCustomer();
        Customer customer = customerMapper.fromGrpcCustomerRequest(customerGrpc);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerServiceOuterClass.SaveCustomerResponse response = CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                .setCustomer(customerMapper.fromCustomer(savedCustomer))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
