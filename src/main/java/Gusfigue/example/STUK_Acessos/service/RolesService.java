package Gusfigue.example.STUK_Acessos.service;

import Gusfigue.example.STUK_Acessos.dto.RoleAtualizadoDTO;
import Gusfigue.example.STUK_Acessos.dto.RolesDTO;
import Gusfigue.example.STUK_Acessos.entity.Roles;
import Gusfigue.example.STUK_Acessos.entity.Usuario;
import Gusfigue.example.STUK_Acessos.repository.RoleRepository;
import Gusfigue.example.STUK_Acessos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolesService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Set<Roles> buscarPorId(Set<Long> ids) {
        Set<Roles> roles = new HashSet<>(roleRepository.findAllById(ids));

        if (roles.size() != ids.size()) {
            throw new EntityNotFoundException(
                    "Uma ou mais Roles não foram encontradas"
            );
        }
        return roles;
    }

    private RolesDTO toDTO(Roles r) {
        return new RolesDTO(
                r.getId_Role(),
                r.getDescricao()
        );
    }

    public Set<Roles> adicionarRoles(String email, RoleAtualizadoDTO roleAtualizadoDTO) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuário não encontrado"));

        Set<Roles> roles = buscarPorId(roleAtualizadoDTO.roles());

        usuario.getRoles().addAll(roles);

        return usuario.getRoles();
    }

    public Set<Roles> removerRoles(String email, RoleAtualizadoDTO roleAtualizadoDTO) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuário não encontrado"));

        Set<Roles> roles = buscarPorId(roleAtualizadoDTO.roles());

        usuario.getRoles().removeAll(roles);

        return usuario.getRoles();
    }

    public Roles buscarRole(String descricao) {
        return roleRepository.findByDescricao(descricao)
                .orElseThrow(() -> new EntityNotFoundException("Role não encontrada"));
    }

    public Page<RolesDTO> listarRoles(int numeroPagina, int tamanho) {
        Pageable pageable = PageRequest.of(numeroPagina, tamanho);
        return roleRepository.findAll(pageable).map(this::toDTO);
    }
}
