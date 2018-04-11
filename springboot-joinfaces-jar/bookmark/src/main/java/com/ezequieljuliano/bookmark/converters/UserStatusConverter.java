package com.ezequieljuliano.bookmark.converters;

import com.ezequieljuliano.bookmark.entities.enums.UserStatus;
import com.ezequieljuliano.bookmark.utilities.Strings;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = UserStatus.class, value = "userStatusConverter")
public class UserStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (!Strings.isEmpty(string)) {
            return UserStatus.of(string);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return ((UserStatus) o).getDescription();
        }
        return "";
    }

}
