package com.fabricio.personal_finance_api.controller;

import java.net.URI;

import com.fabricio.personal_finance_api.model.dto.LoginRequestDTO;
import com.fabricio.personal_finance_api.model.dto.LoginResponseDTO;
import com.fabricio.personal_finance_api.model.dto.UserDTO;
import com.fabricio.personal_finance_api.model.entity.User;
import com.fabricio.personal_finance_api.service.JwtService;
import com.fabricio.personal_finance_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor


public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<User> create(@Valid @RequestBody UserDTO objDto) {
        User obj = service.fromDto(objDto);
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginResponseDTOResponseEntity(@Valid @RequestBody LoginRequestDTO request) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
