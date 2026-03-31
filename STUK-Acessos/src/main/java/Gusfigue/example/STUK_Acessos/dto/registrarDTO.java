package Gusfigue.example.STUK_Acessos.dto;

import Gusfigue.example.STUK_Acessos.entity.UsuarioRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record registrarDTO(

        @NotBlank(message = "Por favor informe um nome")
        String nome,

        @NotBlank(message = "Por favor informe um email")
        @Email(message = "Email invalido, por favor insira novamente!")
        String email,

        @NotBlank(message = "Por favor informe uma senha")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String senha,

        @NotNull(message = "Por favor informe um role de acesso")
        UsuarioRoles roles) {
}
