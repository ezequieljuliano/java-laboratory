package com.ezequieljuliano.bookmark.controllers;

import com.ezequieljuliano.bookmark.entities.Role;
import com.ezequieljuliano.bookmark.repositories.RoleRepository;
import com.ezequieljuliano.bookmark.utilities.CrudController;
import com.ezequieljuliano.bookmark.utilities.PageList;
import com.ezequieljuliano.bookmark.utilities.PageView;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@PageList("role-list.jsf")
@PageView("role-view.jsf")
public class RoleController extends CrudController<Role, RoleRepository> {

}
