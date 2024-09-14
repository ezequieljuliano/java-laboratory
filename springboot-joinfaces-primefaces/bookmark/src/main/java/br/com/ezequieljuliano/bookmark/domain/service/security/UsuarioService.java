package br.com.ezequieljuliano.bookmark.domain.service.security;

import br.com.ezequieljuliano.bookmark.application.extension.Entities;
import br.com.ezequieljuliano.bookmark.application.extension.Strings;
import br.com.ezequieljuliano.bookmark.domain.entity.security.Papel;
import br.com.ezequieljuliano.bookmark.domain.entity.security.Permissao;
import br.com.ezequieljuliano.bookmark.domain.entity.security.Usuario;
import br.com.ezequieljuliano.bookmark.domain.exception.DefaultException;
import br.com.ezequieljuliano.bookmark.domain.gateway.security.PermissaoGateway;
import br.com.ezequieljuliano.bookmark.domain.gateway.security.UsuarioGateway;
import br.com.ezequieljuliano.bookmark.domain.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioGateway> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final PermissaoGateway permissaoGateway;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder,
                          PermissaoGateway permissaoGateway) {
        this.passwordEncoder = passwordEncoder;
        this.permissaoGateway = permissaoGateway;
    }

    public void confirmarPassword(Usuario usuario, String password, String confirmation) {
        if (!Entities.isUpdateMode(usuario)) {
            if (Strings.isEmpty(password)) {
                throw new DefaultException("Você deve informar uma senha.");
            }
            if (Strings.isEmpty(confirmation)) {
                throw new DefaultException("Você deve informar uma confirmação senha.");
            }
        }
        if (!Strings.isEmpty(password) || !Strings.isEmpty(confirmation)) {
            if (!password.equals(confirmation)) {
                throw new DefaultException("A senha informada não confere com a confirmação.");
            }
            usuario.setPassword(passwordEncoder.encode(password));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = getGateway().findByUsername(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (usuario.get().getAdministrador()) {
            permissaoGateway.findAll().forEach(permissao -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s_%s", permissao.getRecurso(), permissao.getAcao())));
            });
        } else {
            for (Papel papel : usuario.get().getPapeis()) {
                for (Permissao permissao : papel.getPermissoes()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s_%s", permissao.getRecurso(), permissao.getAcao())));
                }
            }
        }
        return User.withUsername(usuario.get().getUsername())
                .password(usuario.get().getPassword())
                .authorities(grantedAuthorities)
                .build();
    }

}
