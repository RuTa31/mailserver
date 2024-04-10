package com.Qpay.costumer.dto;

import com.Qpay.costumer.constant.Role;
import com.Qpay.costumer.token.Token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;

}
