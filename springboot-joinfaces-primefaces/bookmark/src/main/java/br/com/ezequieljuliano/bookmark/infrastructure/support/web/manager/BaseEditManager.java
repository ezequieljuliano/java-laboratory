package br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager;

import br.com.ezequieljuliano.bookmark.infrastructure.support.service.CrudService;
import br.com.ezequieljuliano.bookmark.infrastructure.utilities.IdUtils;
import br.com.ezequieljuliano.bookmark.infrastructure.utilities.MessageSeverity;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public abstract class BaseEditManager<Entity, Service extends CrudService<Entity>> extends BaseManager<Entity, Service> implements Serializable {

    private UUID id;
    private Entity entity;

    @PostConstruct
    public void init() {
        id = null;
        String parameterId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (parameterId != null && !parameterId.trim().isEmpty()) {
            id = UUID.fromString(parameterId);
        }
    }

    public Entity getEntity() {
        if (entity == null) {
            if (id != null) {
                entity = findOne(id);
            } else {
                entity = createEntity();
            }
        }
        return entity;
    }

    public boolean isUpdateMode() {
        return id != null;
    }

    public String insert() {
        id = null;
        entity = createEntity();
        return getEditPageView();
    }

    public String save(Entity entity) {
        try {
            onBeforeSave(entity);
            getService().save(entity);
            onAfterSave(entity);
            id = IdUtils.getValue(entity);
            getMessageContext().add("Registro salvo com sucesso.", MessageSeverity.INFO);
            return getEditPageView().concat("&id=").concat(id.toString());
        } catch (Exception e) {
            log.error(getClass().getName(), e);
            getMessageContext().add("Não foi possível salvar o registro: {0}", MessageSeverity.ERROR, e.getMessage());
        }
        return "";
    }

    public String delete(Entity entity) {
        try {
            onBeforeDelete(entity);
            getService().delete(entity);
            onAfterDelete(entity);
            getMessageContext().add("Registro excluído com sucesso.", MessageSeverity.INFO);
        } catch (Exception e) {
            log.error(getClass().getName(), e);
            getMessageContext().add("Não foi possível excluir o registro: {0}", MessageSeverity.ERROR, e.getMessage());
        }
        return getListPageView();
    }

    @SuppressWarnings("unchecked")
    protected Entity createEntity() {
        Class<Entity> entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error(getClass().getName(), e);
            getMessageContext().add("Ocorreu um erro ao criar o registro: {0}", MessageSeverity.ERROR, e.getMessage());
        }
        return null;
    }

    protected Entity findOne(UUID id) {
        Optional<Entity> entity = getService().findById(id);
        return entity.orElse(null);
    }

    protected void onBeforeSave(Entity entity) {
    }

    protected void onAfterSave(Entity entity) {
    }

    protected void onBeforeDelete(Entity entity) {
    }

    protected void onAfterDelete(Entity entity) {
    }

}
