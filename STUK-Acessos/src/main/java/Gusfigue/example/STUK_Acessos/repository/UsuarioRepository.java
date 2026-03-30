package Gusfigue.example.STUK_Acessos.repository;

import Gusfigue.example.STUK_Acessos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    UserDetails findByEmail(String email);

    Void deleteByEmail(String email);
}
