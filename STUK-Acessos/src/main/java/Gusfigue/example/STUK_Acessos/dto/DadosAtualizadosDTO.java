package Gusfigue.example.STUK_Acessos.dto;

import Gusfigue.example.STUK_Acessos.entity.UsuarioRoles;

public record DadosAtualizadosDTO(String senha, UsuarioRoles roles) {

    public String Senha() {
        return this.senha;
    }

    public UsuarioRoles getRoles() {
        return this.roles;
    }
}
