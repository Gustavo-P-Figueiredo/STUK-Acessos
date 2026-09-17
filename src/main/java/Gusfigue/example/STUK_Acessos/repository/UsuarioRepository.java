package Gusfigue.example.STUK_Acessos.repository;

import Gusfigue.example.STUK_Acessos.entity.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //usado para otimizar consultas ao banco de dados. Realiza relacionamentos N+1 somente quando nescessario
    @EntityGraph(attributePaths = {
            "roles",
            "roles.permissao"
    })
    Optional<Usuario> findByEmail(String email);

    void deleteByEmail(String email);
}
