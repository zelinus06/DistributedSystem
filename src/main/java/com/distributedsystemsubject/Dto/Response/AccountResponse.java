package com.distributedsystemsubject.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponse {
    private String username;
    private String password;
}
