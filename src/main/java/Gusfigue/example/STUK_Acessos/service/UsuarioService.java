package Gusfigue.example.STUK_Acessos.service;

import Gusfigue.example.STUK_Acessos.dto.*;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    private UsuarioDTO toDTO(Usuario u) {
        Set<RolesDTO> roles = u.getRoles().stream()
                .map(role -> new RolesDTO(
                        role.getId_Role(),
                        role.getDescricao()
                ))
                .collect(Collectors.toSet());

        return new UsuarioDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                roles,
                u.isAtivo()
        );
    }

    private Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));
    }

    public Usuario deletarUsuario(String email) {
        Usuario usuario = buscarPorEmail(email);

        usuarioRepository.deleteByEmail(email);
        return usuario;
    }

    public Usuario buscarUsuario(String email) {
        Usuario usuario = buscarPorEmail(email);
        return usuario;
    }

    public Page<UsuarioDTO> listarUsuarios(int numeroPagina, int tamanho) {
        Pageable pageable = PageRequest.of(numeroPagina, tamanho);
        return usuarioRepository.findAll(pageable).map(this::toDTO);
    }

    public UsuarioAtualizadosDTO atualizarDadosUsuario(String email, UsuarioAtualizadosDTO dadosAtualizados) {
        Usuario usuario = buscarPorEmail(email);

        if (dadosAtualizados.nome() != null) {
            usuario.setNome(dadosAtualizados.nome());
        }

        if(dadosAtualizados.email() != null) {
            usuario.setEmail(dadosAtualizados.email());
        }

        return dadosAtualizados;
    }

}
