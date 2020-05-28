package com.ezequieljuliano.bookmark.configuration;

import com.ezequieljuliano.bookmark.entities.Role;
import com.ezequieljuliano.bookmark.entities.User;
import com.ezequieljuliano.bookmark.entities.enums.UserStatus;
import com.ezequieljuliano.bookmark.repositories.RoleRepository;
import com.ezequieljuliano.bookmark.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent e) {
        createRoleIfNotExists("ADMIN", "Administrador");
        createRoleIfNotExists("USER", "Usuário");
        createUserIfNotExists(getAdminUser());
        createUserIfNotExists(getNormalUser());
    }

    private void createRoleIfNotExists(String name, String description) {
        if (roleRepository.findByName(name) == null) {
            Role role = new Role();
            role.setName(name);
            role.setDescription(description);
            roleRepository.save(role);
        }
    }

    private void createUserIfNotExists(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            userRepository.save(user);
        }
    }

    private User getAdminUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setName("Administrador");
        user.setEmail("administrador@bookmark.com");
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(roleRepository.findAll());
        return user;
    }

    private User getNormalUser() {
        User user = new User();
        user.setUsername("usuario");
        user.setPassword("usuario");
        user.setName("Usuário");
        user.setEmail("usuario@bookmark.com");
        user.setStatus(UserStatus.ACTIVE);
        user.getRoles().add(roleRepository.findByName("USER"));
        return user;
    }

}
