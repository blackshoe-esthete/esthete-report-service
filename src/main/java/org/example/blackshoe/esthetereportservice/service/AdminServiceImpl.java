package org.example.blackshoe.esthetereportservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.AdminDto;
import org.example.blackshoe.esthetereportservice.dto.JwtDto;
import org.example.blackshoe.esthetereportservice.entity.Admin;
import org.example.blackshoe.esthetereportservice.repository.AdminRepository;
import org.example.blackshoe.esthetereportservice.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service @Slf4j @RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public JwtDto.JwtResponseDto login(AdminDto.LoginRequest request) {

        final Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));

        if(!passwordEncoder.matches(request.getPassword(), admin.getPassword())){
            throw new NotFoundException("비밀번호가 일치하지 않습니다.");
        }

        JwtDto.JwtRequestDto jwtRequestDto = JwtDto.JwtRequestDto.builder()
                .adminId(admin.getAdminId())
                .email(admin.getEmail())
                .build();

        String accessToken = jwtTokenProvider.createAccessToken(jwtRequestDto);

        JwtDto.JwtResponseDto jwtResponseDto = JwtDto.JwtResponseDto.builder()
                .adminId(admin.getAdminId())
                .jwt(accessToken)
                .build();

        return jwtResponseDto;
    }
}
