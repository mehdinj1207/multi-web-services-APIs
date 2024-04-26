package net.njema.customerdataservice.web;

import net.njema.customerdataservice.dtos.CustomerRequest;
import net.njema.customerdataservice.entities.Customer;
import net.njema.customerdataservice.mapper.CustomerMapper;
import net.njema.customerdataservice.repositories.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerGraphQlController {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerGraphQlController(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    @QueryMapping
    public List<Customer> allCustomers(){
        return customerRepository.findAll();
    }
    @QueryMapping
    public Customer customerById(@Argument Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer==null) throw new RuntimeException(String.format("Customer %s not found",id));
        return customer;
    }
    @MutationMapping
    public Customer saveCustomer(@Argument CustomerRequest customerRequest){
        Customer customer = customerMapper.from(customerRequest);
        return customerRepository.save(customer);
    }
}
