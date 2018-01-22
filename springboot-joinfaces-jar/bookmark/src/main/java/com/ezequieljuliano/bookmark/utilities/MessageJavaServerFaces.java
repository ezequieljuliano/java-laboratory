package com.ezequieljuliano.bookmark.utilities;

import java.text.MessageFormat;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Component;

@Component
public class MessageJavaServerFaces implements MessageContext {

    @Override
    public void add(String message, MessageSeverity severity, Object... args) {
        MessageFormat messageFormat = new MessageFormat(message);
        switch (severity) {
            case INFO:
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "", messageFormat.format(args)));
                break;
            case WARN:
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "", messageFormat.format(args)));
                break;
            case ERROR:
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", messageFormat.format(args)));
                break;
            case FATAL:
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "", messageFormat.format(args)));
                break;
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    @Override
    public void add(String message, MessageSeverity severity) {
        add(message, severity, (Object[]) null);
    }

    @Override
    public void add(String message, Object... args) {
        add(message, MessageSeverity.INFO, args);
    }

    @Override
    public void add(String message) {
        add(message, MessageSeverity.INFO, (Object[]) null);
    }

}
