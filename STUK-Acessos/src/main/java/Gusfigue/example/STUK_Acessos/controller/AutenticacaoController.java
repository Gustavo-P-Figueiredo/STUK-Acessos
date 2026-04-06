package Gusfigue.example.STUK_Acessos.controller;

import Gusfigue.example.STUK_Acessos.dto.autenticacaoDTO;
import Gusfigue.example.STUK_Acessos.dto.registrarDTO;
import Gusfigue.example.STUK_Acessos.entity.Usuario;
import Gusfigue.example.STUK_Acessos.repository.UsuarioRepository;
import Gusfigue.example.STUK_Acessos.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

        @PostMapping("/registrar")
        public ResponseEntity registrar(@RequestBody @Valid registrarDTO data) {
            var usuarioExistente = this.repository.findByEmail(data.email());;

            System.out.println("E-mail buscado: " + data.email());
            System.out.println("Usuário encontrado no banco? " + (usuarioExistente != null));

            if (this.repository.findByEmail(data.email()).isPresent()) {
                return ResponseEntity.badRequest().body("E-mail já cadastrado");
            }

            String senhaHash = passwordEncoder.encode(data.senha());

            Usuario usuario = new Usuario(
                    data.nome(),
                    data.email(),
                    senhaHash,
                    data.roles()
            );

            this.repository.save(usuario);

            return ResponseEntity.ok().build();
        }

        @PostMapping("/login")
        public ResponseEntity login(@RequestBody @Valid autenticacaoDTO data) {
            var usuarioSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var autenticar = this.authenticationManager.authenticate(usuarioSenha);

            var token = tokenService.GerarToken((Usuario) autenticar.getPrincipal());

            return ResponseEntity.ok("token: " + token);
        }

}
