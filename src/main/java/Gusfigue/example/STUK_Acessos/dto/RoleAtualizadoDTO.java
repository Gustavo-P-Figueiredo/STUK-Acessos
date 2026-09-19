package Gusfigue.example.STUK_Acessos.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record RoleAtualizadoDTO(
        @NotEmpty
        Set<Long> roles
) {}
