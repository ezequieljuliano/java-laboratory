package com.ezequieljuliano.bookmark.converters;

import com.ezequieljuliano.bookmark.entities.enums.UserStatus;
import com.ezequieljuliano.bookmark.utilities.Strings;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = UserStatus.class, value = "userStatusConverter")
public class UserStatusConverter implements Converter<UserStatus> {

    @Override
    public UserStatus getAsObject(FacesContext fc, UIComponent uic, String str) {
        if (!Strings.isEmpty(str)) {
            return UserStatus.of(str);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, UserStatus obj) {
        if (obj != null) {
            return obj.getDescription();
        }
        return "";
    }

}
