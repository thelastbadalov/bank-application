package com.example.bankapplication.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class CreateAccountRequest extends BaseAccountRequest{
private String id;
}
