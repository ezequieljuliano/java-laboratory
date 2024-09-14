package br.com.ezequieljuliano.bookmark.application.faces.converter;

import br.com.ezequieljuliano.bookmark.application.extension.Ids;
import br.com.ezequieljuliano.bookmark.application.extension.Strings;
import br.com.ezequieljuliano.bookmark.domain.service.CrudService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public abstract class EntityConverter<Entity, Service extends CrudService<Entity>>
        implements Converter<Entity> {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private Service service;

    @Override
    public Entity getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (!Strings.isEmpty(value)) {
            try {
                Optional<Entity> entity = service.findById(UUID.fromString(value));
                if (entity.isPresent()) {
                    return entity.get();
                }
            } catch (Exception e) {
                log.error("Problema de conversão de dados: {}", value, e);
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema de conversão de dados", "O valor informado é inválido"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Entity entity) {
        if (entity != null) {
            return String.valueOf(Ids.getValue(entity));
        }
        return null;
    }

}
