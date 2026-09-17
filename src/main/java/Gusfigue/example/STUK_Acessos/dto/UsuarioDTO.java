package Gusfigue.example.STUK_Acessos.dto;


import Gusfigue.example.STUK_Acessos.entity.Roles;

public record UsuarioDTO(int id, String nome, String email, Roles roles) {
}
