package com.distributedsystemsubject.Dto;

import com.distributedsystemsubject.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String role;
    private String token;
}
