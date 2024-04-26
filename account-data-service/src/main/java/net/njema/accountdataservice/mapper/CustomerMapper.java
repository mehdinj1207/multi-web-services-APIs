package net.njema.accountdataservice.mapper;

import net.njema.accountdataservice.model.Customer;
import net.njema.customerdataservice.stub.CustomerServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();
    public Customer fromCustomer(net.njema.customerdataservice.web.Customer customerSoap){
        return modelMapper.map(customerSoap,Customer.class);
    }
    public  Customer fromGrpcCustomer(CustomerServiceOuterClass.Customer grpcCustomer){
        return modelMapper.map(grpcCustomer, Customer.class);
    }
}
