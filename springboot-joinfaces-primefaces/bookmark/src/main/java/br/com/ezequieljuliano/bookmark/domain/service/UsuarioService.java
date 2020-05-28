package br.com.ezequieljuliano.bookmark.domain.service;

import br.com.ezequieljuliano.bookmark.domain.model.Papel;
import br.com.ezequieljuliano.bookmark.domain.model.Permissao;
import br.com.ezequieljuliano.bookmark.domain.model.Usuario;
import br.com.ezequieljuliano.bookmark.domain.repository.PermissaoRepository;
import br.com.ezequieljuliano.bookmark.domain.repository.UsuarioRepository;
import br.com.ezequieljuliano.bookmark.infrastructure.exception.ServiceException;
import br.com.ezequieljuliano.bookmark.infrastructure.support.service.BaseCrudService;
import br.com.ezequieljuliano.bookmark.infrastructure.utilities.EntityUtils;
import br.com.ezequieljuliano.bookmark.infrastructure.utilities.Strings;
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
public class UsuarioService extends BaseCrudService<Usuario, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final PermissaoRepository permissaoRepository;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder,
                          PermissaoRepository permissaoRepository) {
        this.passwordEncoder = passwordEncoder;
        this.permissaoRepository = permissaoRepository;
    }

    public void confirmarPassword(Usuario usuario, String password, String confirmation) {
        if (!EntityUtils.isUpdateMode(usuario)) {
            if (Strings.isEmpty(password)) {
                throw new ServiceException("Você deve informar uma senha.");
            }
            if (Strings.isEmpty(confirmation)) {
                throw new ServiceException("Você deve informar uma confirmação senha.");
            }
        }
        if (!Strings.isEmpty(password) || !Strings.isEmpty(confirmation)) {
            if (!password.equals(confirmation)) {
                throw new ServiceException("A senha informada não confere com a confirmação.");
            }
            usuario.setPassword(passwordEncoder.encode(password));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = getRepository().findByUsername(username);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (usuario.get().getAdministrador()) {
            permissaoRepository.findAll().forEach(permissao -> {
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
