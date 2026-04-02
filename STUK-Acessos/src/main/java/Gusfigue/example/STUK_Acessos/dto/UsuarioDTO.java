package Gusfigue.example.STUK_Acessos.dto;

import Gusfigue.example.STUK_Acessos.entity.UsuarioRoles;

public record UsuarioDTO(int id, String nome, String email, UsuarioRoles roles) {
}
