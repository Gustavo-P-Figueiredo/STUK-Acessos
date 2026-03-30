package Gusfigue.example.STUK_Acessos.dto;

import Gusfigue.example.STUK_Acessos.entity.UsuarioRoles;

public record registrarDTO(String nome, String email, String senha, UsuarioRoles roles) {
}
