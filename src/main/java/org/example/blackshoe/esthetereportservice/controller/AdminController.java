package org.example.blackshoe.esthetereportservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.AdminDto;
import org.example.blackshoe.esthetereportservice.dto.JwtDto;
import org.example.blackshoe.esthetereportservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<JwtDto.JwtResponseDto> login(
            @RequestBody AdminDto.LoginRequest request
    ) {
        log.info("login");

        JwtDto.JwtResponseDto response = adminService.login(request);

        return ResponseEntity.ok(response);
    }

}
