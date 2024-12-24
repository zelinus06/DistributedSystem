package com.distributedsystemsubject.Controller.AdminController;

import com.distributedsystemsubject.Dto.Request.AccountRequest;
import com.distributedsystemsubject.Dto.Response.AccountResponse;
import com.distributedsystemsubject.Service.AdminService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    private AccountService accountService;

    @PostMapping("createAccount")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.createAccount(accountRequest.getUsername(), accountRequest.getRole());
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewAccount() {
        return ResponseEntity.ok(accountService.viewAccount());
    }
}
