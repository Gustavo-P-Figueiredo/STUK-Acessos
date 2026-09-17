package Gusfigue.example.STUK_Acessos.segurity;

import Gusfigue.example.STUK_Acessos.entity.Usuario;
import Gusfigue.example.STUK_Acessos.repository.UsuarioRepository;
import Gusfigue.example.STUK_Acessos.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository  usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            try {
                    var subject = tokenService.ValidarToken(token);
                    Optional<Usuario> user = usuarioRepository.findByEmail(subject);

                    UsernamePasswordAuthenticationToken authentication = null;
                    if (user.isPresent()) {
                        authentication = new UsernamePasswordAuthenticationToken(
                                user.get(), null, user.get().getAuthorities());
                    }
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            catch (HttpClientErrorException.Unauthorized exception) {
                SecurityContextHolder.clearContext();

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
            filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.replace("Bearer ", "");
    }

}
