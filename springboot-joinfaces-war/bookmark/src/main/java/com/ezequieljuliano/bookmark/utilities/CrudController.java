package com.ezequieljuliano.bookmark.utilities;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudController<T, R extends JpaRepository<T, Long>> implements Serializable {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private MessageContext messageContext;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private R repository;

    private List<T> dataSet;

    private T record;

    private Long id;

    @Getter
    @Setter
    private T selected;

    private String viewPage;

    private String listPage;

    private Long getRecordIdValue(T record) {
        try {
            Method getIdMethod = record.getClass().getMethod("getId");
            if (getIdMethod != null) {
                return (Long) getIdMethod.invoke(record);
            }
        } catch (Exception e) {
            Logger.getLogger(CrudController.class.getName()).log(Level.SEVERE, null, e);
            messageContext.add(e.getMessage());
        }
        return null;
    }

    private T createRecord() {
        try {
            Class<T> recordClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return recordClass.newInstance();
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

    protected void onSave(T record) {
        repository.save(record);
    }

    protected void onDelete(T record) {
        repository.delete(record);
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

    public List<T> getDataSet() {
        if (dataSet == null) {
            dataSet = findAll();
        }
        return dataSet;
    }

    public T getRecord() {
        if (record == null) {
            if (id != null) {
                record = findOne(id);
            } else {
                record = createRecord();
            }
        }
        return record;
    }

    public String insert() {
        record = createRecord();
        id = null;
        return getViewPage();
    }

    public String edit(T record) {
        this.record = record;
        id = getRecordIdValue(this.record);
        return getViewPage().concat("&id=").concat(id.toString());
    }

    public void save() {
        try {
            onSave(getRecord());
            id = getRecordIdValue(getRecord());
            messageContext.add("Registro salvo com sucesso.", MessageSeverity.INFO);
        } catch (Exception e) {
            messageContext.add("Ocorreu um erro ao salvar o registro: {0}", MessageSeverity.ERROR, e.getMessage());
        }
    }

    public String delete() {
        try {
            onDelete(getRecord());
            messageContext.add("Registro excluído com sucesso.", MessageSeverity.WARN);
        } catch (Exception e) {
            messageContext.add("Ocorreu um erro ao excluir o registro: {0}", MessageSeverity.ERROR, e.getMessage());
        }
        return getListPage();
    }

    public void delete(T record) {
        try {
            onDelete(record);
            getDataSet().remove(record);
            messageContext.add("Registro excluído com sucesso.", MessageSeverity.WARN);
        } catch (Exception e) {
            messageContext.add("Ocorreu um erro ao excluir o registro: {0}", MessageSeverity.ERROR, e.getMessage());
        }
    }

    public void refreshList() {
        dataSet = findAll();
    }

    public void refreshRecord() {
        if (id != null) {
            record = findOne(id);
        }
    }

    public boolean isUpdateMode() {
        return id != null;
    }

}
