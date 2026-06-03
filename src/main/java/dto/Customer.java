package dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer {
    private final String firstName;
    private final String lastName;
    private final String zipCode;
}