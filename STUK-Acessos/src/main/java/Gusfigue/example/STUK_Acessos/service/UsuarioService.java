package Gusfigue.example.STUK_Acessos.service;

import Gusfigue.example.STUK_Acessos.entity.Usuario;
import Gusfigue.example.STUK_Acessos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario deletarUsuario(String email) {
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));
        usuarioRepository.deleteByEmail(email);
        return usuario;
    }

    public Usuario buscarUsuario(String email) {
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizarDadosUsuario(String email, Usuario dadosAtualizados) {
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));

        if(dadosAtualizados.getSenha() != null)
            usuario.setSenha(dadosAtualizados.getSenha());

        if (dadosAtualizados.getSenha() != null &&  !dadosAtualizados.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(dadosAtualizados.getSenha()));
        }

        if (dadosAtualizados.getRoles() != null)
            usuario.setRoles(dadosAtualizados.getRoles());

        return usuarioRepository.save(usuario);
    }
}
