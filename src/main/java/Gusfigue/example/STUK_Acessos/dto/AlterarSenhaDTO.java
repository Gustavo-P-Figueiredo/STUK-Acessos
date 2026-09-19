package Gusfigue.example.STUK_Acessos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterarSenhaDTO(
        @NotBlank
        String senhaAtual,

        @NotBlank
        @Size(min = 8)
        String novaSenha
) {}
