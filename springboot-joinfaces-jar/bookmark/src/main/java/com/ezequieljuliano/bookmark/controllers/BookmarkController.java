package com.ezequieljuliano.bookmark.controllers;

import com.ezequieljuliano.bookmark.entities.Bookmark;
import com.ezequieljuliano.bookmark.entities.Category;
import com.ezequieljuliano.bookmark.repositories.BookmarkRepository;
import com.ezequieljuliano.bookmark.repositories.CategoryRepository;
import com.ezequieljuliano.bookmark.utilities.CrudController;
import com.ezequieljuliano.bookmark.utilities.PageList;
import com.ezequieljuliano.bookmark.utilities.PageView;
import com.ezequieljuliano.bookmark.utilities.Strings;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@ViewScoped
@PageList("bookmark-list.jsf")
@PageView("bookmark-view.jsf")
public class BookmarkController extends CrudController<Bookmark, BookmarkRepository> {

    @Autowired
    private CategoryRepository categoryRepository;

    @NotNull
    @NotEmpty
    @Setter
    private String categoryInformation;

    public List<Category> getCategoriesList() {
        return categoryRepository.findAll();
    }

    public String getCategoryInformation() {
        if (getRecord() != null && getRecord().getCategory() != null) {
            categoryInformation = getRecord().getCategory().toString();
        }
        return categoryInformation;
    }

    @Override
    protected void onSave(Bookmark bookmark) {
        if (!Strings.isEmpty(categoryInformation)) {
            Category category = categoryRepository.getOne(Strings.getId(categoryInformation));
            bookmark.setCategory(category);
        } else {
            bookmark.setCategory(null);
        }
        super.onSave(bookmark);
    }

}
