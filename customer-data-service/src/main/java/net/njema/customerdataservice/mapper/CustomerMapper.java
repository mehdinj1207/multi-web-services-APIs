package net.njema.customerdataservice.mapper;

import net.njema.customerdataservice.dtos.CustomerRequest;
import net.njema.customerdataservice.entities.Customer;
import net.njema.customerdataservice.stub.CustomerServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();
    public  Customer from(CustomerRequest customerRequest){
        return modelMapper.map(customerRequest, Customer.class);
    }

    public CustomerServiceOuterClass.Customer fromCustomer(Customer customer){
        return modelMapper.map(customer, CustomerServiceOuterClass.Customer.Builder.class).build();
    }
    public Customer fromGrpcCustomerRequest(CustomerServiceOuterClass.CustomerRequest customer){
        return modelMapper.map(customer,Customer.class);
    }
}
