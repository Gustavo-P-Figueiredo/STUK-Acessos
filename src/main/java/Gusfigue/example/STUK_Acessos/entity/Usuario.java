package Gusfigue.example.STUK_Acessos.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Usuario")
@Table(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    @ManyToMany
    @JoinTable(
            name = "usuario_role",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Roles> roles;

    private boolean ativo;

    private int tentativa_login;

    public Usuario(String nome, String email, String senha, Set<Roles> roles, Boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.roles = roles;
        this.ativo = ativo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (Roles role : roles) {
            authorities.add(
                    new SimpleGrantedAuthority("ROLE_" + role.getDescricao())
            );

            for (Permissao permissao : role.getPermissao()) {
                authorities.add(
                        new SimpleGrantedAuthority(permissao.getNome())
                );
            }
        }
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }

}
