package com.distributedsystemsubject.Dto.Request;

import com.distributedsystemsubject.Enums.Role;
import lombok.Data;

@Data
public class AccountRequest {
    private String username;
    private com.distributedsystemsubject.Enums.Role Role;
}
