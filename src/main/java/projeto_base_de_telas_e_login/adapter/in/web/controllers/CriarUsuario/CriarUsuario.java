package projeto_base_de_telas_e_login.adapter.in.web.controllers.CriarUsuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.in.web.dto.User.LoginResponseDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.User.RegisterDTO;
import projeto_base_de_telas_e_login.adapter.out.persistence.User.UserEntity;
import projeto_base_de_telas_e_login.domain.UseCase.User.UserUseCase;
import projeto_base_de_telas_e_login.domain.model.user.UserRole;
import projeto_base_de_telas_e_login.tudo.security.TokenService;
import projeto_base_de_telas_e_login.adapter.out.persistence.User.UserRepository;

import projeto_base_de_telas_e_login.adapter.in.web.dto.User.UpdateUserDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.User.UserResponseDTO;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/auth")
public class CriarUsuario {

    private final UserUseCase userUseCase;
    private final TokenService tokenService;

    public CriarUsuario(UserUseCase userUseCase, TokenService tokenService) {
        this.userUseCase = userUseCase;
        this.tokenService = tokenService;
    }

    // 🔐 REGISTRO
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(
            @RequestBody @Valid RegisterDTO dto
    ) {
        var user = userUseCase.createUser(
                dto.login(),
                dto.password(),
                dto.role()
        );

        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    // 🔐 LISTAR USUÁRIOS (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponseDTO>> listarUsuarios() {

        var users = userUseCase.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getRole().name()
                ))
                .toList();

        return ResponseEntity.ok(users);
    }


    // 🔐 ATUALIZAR USUÁRIO (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/users/{id}")
    public ResponseEntity<Void> atualizarUsuario(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserDTO dto
    ) {
        userUseCase.updateUser(
                id,
                dto.login(),
                dto.password(),
                dto.role()
        );

        return ResponseEntity.ok().build();
    }
}
