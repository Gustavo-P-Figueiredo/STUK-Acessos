package Gusfigue.example.STUK_Acessos.dto;


import java.util.Set;

public record UsuarioDTO(Long id, String nome, String email, Set<RolesDTO> roles, boolean ativo) {
}
