package com.ezequieljuliano.bookmark.infrastructure.security;

import com.ezequieljuliano.bookmark.domain.entities.Role;
import com.ezequieljuliano.bookmark.domain.entities.User;
import com.ezequieljuliano.bookmark.domain.repositories.RoleRepository;
import com.ezequieljuliano.bookmark.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
@Transactional
public class SecurityDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private void addDefaultRoleIfNotFound(Role role) {
        if (roleRepository.findByName(role.getName()) == null) {
            roleRepository.save(role);
        }
    }

    private void addDefaultUserIfNotFound(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            userRepository.save(user);
        }
    }

    private Role createAdminRole() {
        Role result = new Role();
        result.setName("ADMIN");
        result.setDescription("Administrador");
        return result;
    }

    private Role createUserRole() {
        Role result = new Role();
        result.setName("USER");
        result.setDescription("Usuário");
        return result;
    }

    private User createAdminUser() {
        User result = new User();
        result.setUsername("admin");
        result.setPassword("admin");
        result.setName("Administrador");
        result.setEmail("administrador@bookmark.com");
        result.setStatus(User.Status.ACTIVE);
        result.setRoles(roleRepository.findAll());
        return result;
    }

    private User createUser() {
        User result = new User();
        result.setUsername("usuario");
        result.setPassword("usuario");
        result.setName("Usuário");
        result.setEmail("usuario@bookmark.com");
        result.setStatus(User.Status.ACTIVE);
        result.getRoles().add(roleRepository.findByName("USER"));
        return result;
    }

    @Override
    public void run(String... args) throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Authentication authentication = new UsernamePasswordAuthenticationToken("admin", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        addDefaultRoleIfNotFound(createAdminRole());
        addDefaultRoleIfNotFound(createUserRole());
        addDefaultUserIfNotFound(createAdminUser());
        addDefaultUserIfNotFound(createUser());
    }
}
