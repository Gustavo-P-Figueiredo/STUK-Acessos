package Gusfigue.example.STUK_Acessos.repository;

import Gusfigue.example.STUK_Acessos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<UserDetails> findByEmail(String email);

    void deleteByEmail(String email);
}
