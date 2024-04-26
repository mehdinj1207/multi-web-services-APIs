package net.njema.customerdataservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String email;
}
