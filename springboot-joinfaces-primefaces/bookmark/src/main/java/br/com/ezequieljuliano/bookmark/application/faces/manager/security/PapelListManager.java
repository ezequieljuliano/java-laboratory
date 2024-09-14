package br.com.ezequieljuliano.bookmark.application.faces.manager.security;

import br.com.ezequieljuliano.bookmark.application.faces.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.application.faces.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.application.faces.manager.ListManager;
import br.com.ezequieljuliano.bookmark.domain.entity.security.Papel;
import br.com.ezequieljuliano.bookmark.domain.service.security.PapelService;
import jakarta.faces.view.ViewScoped;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ViewScoped
@ListPageView("papel-list.jsf")
@EditPageView("papel-edit.jsf")
public class PapelListManager extends ListManager<Papel, PapelService>
        implements Serializable {

}
