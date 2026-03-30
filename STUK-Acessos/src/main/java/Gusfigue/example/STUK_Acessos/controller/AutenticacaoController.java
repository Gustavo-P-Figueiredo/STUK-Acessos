package Gusfigue.example.STUK_Acessos.controller;

import Gusfigue.example.STUK_Acessos.dto.autenticacaoDTO;
import Gusfigue.example.STUK_Acessos.dto.registrarDTO;
import Gusfigue.example.STUK_Acessos.entity.Usuario;
import Gusfigue.example.STUK_Acessos.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity login(@RequestBody @Valid autenticacaoDTO data) {
        var usuarioSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var autenticar = this.authenticationManager.authenticate(usuarioSenha);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    @RequestMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid registrarDTO data) {
        if (this.repository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();

            String senhaHash = new BCryptPasswordEncoder().encode(data.senha());
            Usuario usuario = new Usuario(data.email(), senhaHash, data.nome(), data.roles());

            this.repository.save(usuario);

            return ResponseEntity.ok().build();
    }

}
