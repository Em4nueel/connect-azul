package com.connect.jpa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.web.bind.annotation.*;

import com.connect.jpa.model.AuthRequest;
import com.connect.jpa.model.ClinicaOuHospitalModel;
import com.connect.jpa.model.Usuario;
import com.connect.jpa.repository.ClinicaOuHospitalRepository;
import com.connect.jpa.repository.UserInfoRepository;
import com.connect.jpa.service.JwtService;
import com.connect.jpa.service.LogoutService;
import com.connect.jpa.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
  
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints de autenticação e registro de usuários") 
public class UserController { 
    private final JwtService jwtService; 
    private final AuthenticationManager authenticationManager; 
    private final LogoutService logoutService;
    private final UsuarioService service;
    private final UserInfoRepository usuarioRepository;
    private final ClinicaOuHospitalRepository clinicaRepository;

    UserController(UserInfoRepository usuarioRepository, ClinicaOuHospitalRepository clinicaRepository, UsuarioService service, JwtService jwtService, AuthenticationManager authenticationManager, LogoutService logoutService) {
        this.logoutService = logoutService;
        this.jwtService = jwtService; 
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.usuarioRepository = usuarioRepository;
        this.clinicaRepository = clinicaRepository;
    }
  
    @PostMapping("/generateToken")
@Operation(
    summary = "Gerar Token de Autenticação",
    description = "Autentica usuário e gera token JWT"
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Token gerado com sucesso"),
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
})
public ResponseEntity<Map<String, Object>> authenticateAndGetToken(
    @Parameter(description = "Credenciais de autenticação")
    @RequestBody AuthRequest authRequest
) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authRequest.getUsername(),
            authRequest.getPassword()
        )
    );

    if (authentication.isAuthenticated()) {
        // Buscar o usuário autenticado
        Usuario usuario = usuarioRepository.findByNome(authRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Verificar se o usuário está associado a uma clínica
        ClinicaOuHospitalModel clinica = clinicaRepository.findByUsuarioId(usuario.getId()).orElse(null);

        // Gerar o token
        String token = jwtService.generateToken(authRequest.getUsername(), 
                            clinica != null ? clinica.getId() : null);

        // Montar a resposta
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        if (clinica != null) {
            response.put("clinicaId", clinica.getId());
        }

        return ResponseEntity.ok(response);
    } else {
        throw new UsernameNotFoundException("Invalid user request!");
    }
}

    @PostMapping("/register")
    @Operation(
        summary = "Registrar Novo Usuário",
        description = "Cadastra um novo usuário no sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para registro")
    })
    public ResponseEntity<Usuario> addNewUser(
        @Parameter(description = "Informações do usuário") 
        @RequestBody Usuario userInfo
    ) { 
        Usuario response = service.addUser(userInfo); 
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 

    @DeleteMapping("/logout")
    @Operation(
        summary = "Logout de Usuário", 
        description = "Revoga o token de autenticação do usuário"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Logout realizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao realizar logout")
    })
    public ResponseEntity<String> logout(
        @Parameter(description = "Token de autorização") 
        @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "");
        logoutService.revokeToken(token);
        return ResponseEntity.ok("Logout realizado com sucesso");
    }
  
}
