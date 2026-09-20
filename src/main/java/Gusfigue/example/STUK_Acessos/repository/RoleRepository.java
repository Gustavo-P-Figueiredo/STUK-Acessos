package Gusfigue.example.STUK_Acessos.repository;

import Gusfigue.example.STUK_Acessos.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByDescricao(String descricao);

}
