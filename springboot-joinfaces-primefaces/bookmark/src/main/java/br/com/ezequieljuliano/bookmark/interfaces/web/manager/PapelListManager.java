package br.com.ezequieljuliano.bookmark.interfaces.web.manager;

import br.com.ezequieljuliano.bookmark.domain.model.Papel;
import br.com.ezequieljuliano.bookmark.domain.service.PapelService;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager.BaseListManager;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@ListPageView("papel-list.jsf")
@EditPageView("papel-edit.jsf")
public class PapelListManager extends BaseListManager<Papel, PapelService> implements Serializable {

}
