package com.example.bankapplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerRequest extends BaseCustomerRequest{
private String id;

}
