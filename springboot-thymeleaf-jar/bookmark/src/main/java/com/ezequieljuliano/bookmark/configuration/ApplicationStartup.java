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
        insertDefaultRoles();
        insertDefaultUsers();
    }

    private void insertDefaultRoles() {
        if (roleRepository.findByName("ADMIN") == null) {
            Role role = new Role();
            role.setName("ADMIN");
            role.setDescription("Administrador");
            roleRepository.save(role);
        }
        if (roleRepository.findByName("USER") == null) {
            Role role = new Role();
            role.setName("USER");
            role.setDescription("Usuário");
            roleRepository.save(role);
        }
    }

    private void insertDefaultUsers() {
        if (userRepository.findByUsername("admin") == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setName("Administrador");
            user.setEmail("administrador@bookmark.com");
            user.setStatus(UserStatus.ACTIVE);
            user.setRoles(roleRepository.findAll());
            userRepository.save(user);
        }
        if (userRepository.findByUsername("usuario") == null) {
            User user = new User();
            user.setUsername("usuario");
            user.setPassword("usuario");
            user.setName("Usuário");
            user.setEmail("usuario@bookmark.com");
            user.setStatus(UserStatus.ACTIVE);
            user.getRoles().add(roleRepository.findByName("USER"));
            userRepository.save(user);
        }
    }

}
