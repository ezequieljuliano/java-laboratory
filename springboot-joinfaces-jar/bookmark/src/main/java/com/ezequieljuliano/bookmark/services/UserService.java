package com.ezequieljuliano.bookmark.services;

import com.ezequieljuliano.bookmark.entities.User;
import com.ezequieljuliano.bookmark.repositories.UserRepository;
import com.ezequieljuliano.bookmark.utilities.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends CrudService<User, UserRepository> {

}
