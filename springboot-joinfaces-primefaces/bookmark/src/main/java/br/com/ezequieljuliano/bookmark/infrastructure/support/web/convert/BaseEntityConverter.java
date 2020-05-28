package br.com.ezequieljuliano.bookmark.infrastructure.support.web.convert;

import br.com.ezequieljuliano.bookmark.infrastructure.support.service.CrudService;
import br.com.ezequieljuliano.bookmark.infrastructure.utilities.IdUtils;
import br.com.ezequieljuliano.bookmark.infrastructure.utilities.Strings;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseEntityConverter<Entity, Service extends CrudService<Entity>> implements Converter<Entity> {

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
                throw new ConverterException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema de conversão de dados", "O valor informado é inválido")
                );
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Entity entity) {
        if (entity != null) {
            return String.valueOf(IdUtils.getValue(entity));
        }
        return null;
    }

}
