package Gusfigue.example.STUK_Acessos.controller;

import Gusfigue.example.STUK_Acessos.dto.UsuarioDTO;
import Gusfigue.example.STUK_Acessos.entity.Usuario;
import Gusfigue.example.STUK_Acessos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    @RequestMapping("/buscar")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@RequestParam String email) {
        Usuario usuario = usuarioService.buscarUsuario(email);

        UsuarioDTO dto = new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRoles()
        );

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        List<UsuarioDTO> listaDTO = usuarios.stream()
                .map(u -> new UsuarioDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getRoles()
        ))
                .toList();

        return ResponseEntity.ok(listaDTO);
    }

    @DeleteMapping
    @RequestMapping("/delete")
    public ResponseEntity<String> deleteUsuario(@RequestParam String email) {
        Usuario usuario = usuarioService.deletarUsuario(email);

        UsuarioDTO dto = new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRoles()
        );

        return ResponseEntity.ok(dto + " deletado");
    }

    @PutMapping
    @RequestMapping("/atualizarUsuario")
    public ResponseEntity<String> atualizarUsuario(@RequestBody Usuario usuario, @RequestParam String email) {
      usuarioService.atualizarDadosUsuario(email, usuario);

      return ResponseEntity.ok("Dados atualizado com sucesso!");
    }
}
