package Gusfigue.example.STUK_Acessos.dto;

import Gusfigue.example.STUK_Acessos.entity.Roles;

public record DadosAtualizadosDTO(String senha, Roles roles) {

    public String Senha() {
        return this.senha;
    }

    public Roles getRoles() {
        return this.roles;
    }
}
