package br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager;

import br.com.ezequieljuliano.bookmark.infrastructure.support.service.CrudService;
import br.com.ezequieljuliano.bookmark.infrastructure.utilities.IdUtils;
import br.com.ezequieljuliano.bookmark.infrastructure.utilities.MessageSeverity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Slf4j
public abstract class BaseListManager<Entity, Service extends CrudService<Entity>> extends BaseManager<Entity, Service> implements Serializable {

    private List<Entity> entities;

    @Getter
    @Setter
    private List<Entity> selectedEntities;

    @Getter
    @Setter
    private List<Entity> filteredEntities;

    public List<Entity> getEntities() {
        if (entities == null) {
            entities = findAll();
        }
        return entities;
    }

    public String insert() {
        return getEditPageView();
    }

    public String edit(Entity entity) {
        UUID id = IdUtils.getValue(entity);
        if (id != null) {
            return getEditPageView().concat("&id=").concat(id.toString());
        }
        return "";
    }

    public String delete(List<Entity> selectedEntities) {
        try {
            if ((selectedEntities != null) && (!selectedEntities.isEmpty())) {
                onBeforeDelete(selectedEntities);
                for (Entity entity : selectedEntities) {
                    getService().delete(entity);
                }
                onAfterDelete(selectedEntities);
                getMessageContext().add("Registros excluídos com sucesso.", MessageSeverity.INFO);
            }
        } catch (Exception e) {
            log.error(getClass().getName(), e);
            getMessageContext().add("Não foi possível excluir os registros: {0}", MessageSeverity.ERROR, e.getMessage());
        }
        return getListPageView();
    }

    protected List<Entity> findAll() {
        return getService().findAll();
    }

    protected void onBeforeDelete(List<Entity> selectedEntities) {
    }

    protected void onAfterDelete(List<Entity> selectedEntities) {
    }

}
