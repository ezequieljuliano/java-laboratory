package br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager;

import br.com.ezequieljuliano.bookmark.infrastructure.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.support.service.CrudService;
import br.com.ezequieljuliano.bookmark.infrastructure.utilities.MessageContext;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class BaseManager<Entity, Service extends CrudService<Entity>> implements Serializable {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private Service service;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private MessageContext messageContext;

    private String listPageView;
    private String editPageView;

    public String getListPageView() {
        if (listPageView == null) {
            ListPageView annotation = this.getClass().getAnnotation(ListPageView.class);
            if (annotation != null) {
                listPageView = annotation.value().concat("?faces-redirect=true");
            }
        }
        return listPageView;
    }

    public String getEditPageView() {
        if (editPageView == null) {
            EditPageView annotation = this.getClass().getAnnotation(EditPageView.class);
            if (annotation != null) {
                editPageView = annotation.value().concat("?faces-redirect=true");
            }
        }
        return editPageView;
    }

}
