package com.ezequieljuliano.bookmark.domain.repositories;

import com.ezequieljuliano.bookmark.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
