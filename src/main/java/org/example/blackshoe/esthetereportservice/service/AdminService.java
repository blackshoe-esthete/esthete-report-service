package org.example.blackshoe.esthetereportservice.service;

import org.example.blackshoe.esthetereportservice.dto.AdminDto;
import org.example.blackshoe.esthetereportservice.dto.JwtDto;

public interface AdminService {
    JwtDto.JwtResponseDto login(AdminDto.LoginRequest request);
}
