package br.com.ezequieljuliano.bookmark.infrastructure.utilities;

import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;

@Component
public class MessageContext {

    public void add(String message, MessageSeverity severity, Object... args) {
        MessageFormat messageFormat = new MessageFormat(message);
        switch (severity) {
            case INFO:
                FacesContext.getCurrentInstance()
                        .addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "", messageFormat.format(args)));
                break;
            case WARN:
                FacesContext.getCurrentInstance()
                        .addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "", messageFormat.format(args)));
                break;
            case ERROR:
                FacesContext.getCurrentInstance()
                        .addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", messageFormat.format(args)));
                break;
            case FATAL:
                FacesContext.getCurrentInstance()
                        .addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "", messageFormat.format(args)));
                break;
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public void add(String message, MessageSeverity severity) {
        add(message, severity, (Object[]) null);
    }

    public void add(String message, Object... args) {
        add(message, MessageSeverity.INFO, args);
    }

    public void add(String message) {
        add(message, MessageSeverity.INFO, (Object[]) null);
    }

}
