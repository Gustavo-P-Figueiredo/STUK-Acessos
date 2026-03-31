package Gusfigue.example.STUK_Acessos.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
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

    @JsonValue
    public String getRoles() {
        return roles;
    }

    @JsonCreator
    public static UsuarioRoles FromString(String value){
        for(UsuarioRoles role : UsuarioRoles.values()){
            if(role.name().equalsIgnoreCase(value)){
                return role;
            }
        }
            throw new IllegalArgumentException("Role: " + value + " invalida");
    }

}
