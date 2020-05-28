package com.ezequieljuliano.bookmark.controllers;

import com.ezequieljuliano.bookmark.entities.Category;
import com.ezequieljuliano.bookmark.repositories.CategoryRepository;
import com.ezequieljuliano.bookmark.utilities.CrudController;
import com.ezequieljuliano.bookmark.utilities.PageList;
import com.ezequieljuliano.bookmark.utilities.PageView;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@PageList("category-list.jsf")
@PageView("category-view.jsf")
public class CategoryController extends CrudController<Category, CategoryRepository> {

}
