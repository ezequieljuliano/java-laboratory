package com.ezequieljuliano.bookmark.utilities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CrudController<T, R extends JpaRepository<T, Long>> implements Serializable {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private MessageContext messageContext;

    @Autowired
    @Lazy
    @Getter(AccessLevel.PROTECTED)
    private R repository;

    private List<T> list;

    private T entity;

    private Long id;

    @Getter
    @Setter
    private T selection;

    private String viewPage;

    private String listPage;

    private Long getIdValue(T entity) {
        try {
            Method getIdMethod = entity.getClass().getMethod("getId");
            if (getIdMethod != null) {
                return (Long) getIdMethod.invoke(entity);
            }
        } catch (Exception e) {
            Logger.getLogger(CrudController.class.getName()).log(Level.SEVERE, null, e);
            messageContext.add(e.getMessage());
        }
        return null;
    }

    private T createEntity() {
        try {
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return entityClass.newInstance();
        } catch (Exception e) {
            Logger.getLogger(CrudController.class.getName()).log(Level.SEVERE, null, e);
            messageContext.add(e.getMessage());
        }
        return null;
    }

    protected List<T> findAll() {
        return repository.findAll();
    }

    protected T findOne(Long id) {
        return repository.getOne(id);
    }

    protected void beforeSave(T entity) {
    }

    protected void afterSave(T entity) {
    }

    protected void doSave(T entity) {
        repository.save(entity);
    }

    protected void beforeDelete(T entity) {
    }

    protected void afterDelete(T entity) {
    }

    protected void doDelete(T entity) {
        repository.delete(entity);
    }

    public String getViewPage() {
        if (viewPage == null) {
            PageView annotation = this.getClass().getAnnotation(PageView.class);
            if (annotation != null) {
                viewPage = annotation.value().concat("?faces-redirect=true");
            }
        }
        return viewPage;
    }

    public String getListPage() {
        if (listPage == null) {
            PageList annotation = this.getClass().getAnnotation(PageList.class);
            if (annotation != null) {
                listPage = annotation.value().concat("?faces-redirect=true");
            }
        }
        return listPage;
    }

    @PostConstruct
    public void init() {
        id = null;
        String requestId = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        if (requestId != null && !requestId.trim().isEmpty()) {
            id = Long.valueOf(requestId);
        }
    }

    public List<T> getList() {
        if (list == null) {
            list = findAll();
        }
        return list;
    }

    public T getEntity() {
        if (entity == null) {
            if (id != null) {
                entity = findOne(id);
            } else {
                entity = createEntity();
            }
        }
        return entity;
    }

    public String insert() {
        entity = createEntity();
        id = null;
        return getViewPage();
    }

    public String edit(T entity) {
        this.entity = entity;
        id = getIdValue(this.entity);
        return getViewPage().concat("&id=").concat(id.toString());
    }

    public void save() {
        try {
            beforeSave(getEntity());
            doSave(getEntity());
            afterSave(getEntity());
            id = getIdValue(getEntity());
            messageContext.add("Registro salvo com sucesso.", MessageSeverity.INFO);
        } catch (Exception e) {
            messageContext.add("Ocorreu um erro ao salvar o registro: {0}", MessageSeverity.ERROR, e.getMessage());
        }
    }

    public String delete() {
        try {
            beforeDelete(getEntity());
            doDelete(getEntity());
            afterDelete(getEntity());
            messageContext.add("Registro excluído com sucesso.", MessageSeverity.WARN);
        } catch (Exception e) {
            messageContext.add("Ocorreu um erro ao excluir o registro: {0}", MessageSeverity.ERROR, e.getMessage());
        }
        return getListPage();
    }

    public void delete(T entity) {
        try {
            beforeDelete(entity);
            doDelete(entity);
            getList().remove(entity);
            afterDelete(entity);
            messageContext.add("Registro excluído com sucesso.", MessageSeverity.WARN);
        } catch (Exception e) {
            messageContext.add("Ocorreu um erro ao excluir o registro: {0}", MessageSeverity.ERROR, e.getMessage());
        }
    }

    public void refreshList() {
        list = findAll();
    }

    public void refreshEntity() {
        if (id != null) {
            entity = findOne(id);
        }
    }

    public boolean isUpdateMode() {
        return id != null;
    }

}
