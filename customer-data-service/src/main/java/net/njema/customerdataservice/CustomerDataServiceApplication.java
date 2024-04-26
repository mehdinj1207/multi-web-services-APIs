package net.njema.customerdataservice;

import net.njema.customerdataservice.entities.Customer;
import net.njema.customerdataservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDataServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(Customer.builder().name("Mehdi").email("mehdi@gmail.com").build());
			customerRepository.save(Customer.builder().name("Mohamed").email("mohamed@gmail.com").build());
			customerRepository.save(Customer.builder().name("Fatma").email("fatma@gmail.com").build());
		};
	}

}
