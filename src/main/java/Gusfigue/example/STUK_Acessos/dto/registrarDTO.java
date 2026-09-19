package Gusfigue.example.STUK_Acessos.dto;

import Gusfigue.example.STUK_Acessos.entity.Roles;
import jakarta.validation.constraints.*;

import java.util.Set;

public record registrarDTO(
        @NotBlank(message = "Por favor informe um nome")
        String nome,

        @NotBlank(message = "Por favor informe um email")
        @Email(message = "Email invalido, por favor insira novamente!")
        String email,

        @NotBlank(message = "Por favor informe uma senha")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String senha,

        @NotEmpty(message = "Por favor informe um role de acesso")
        Set<Long> roles
) {}
