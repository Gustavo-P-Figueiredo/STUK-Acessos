package Gusfigue.example.STUK_Acessos.entity;

public enum UsuarioRoles {

    ADMIN ("admin"),
    USER ("user"),
    LOJISTA ("lojista"),
    OPERADOR ("operador"),
    ENTREGADOR ("entregador");

    private String roles;

    UsuarioRoles(String roles) {
        this.roles = roles;
    }
}
