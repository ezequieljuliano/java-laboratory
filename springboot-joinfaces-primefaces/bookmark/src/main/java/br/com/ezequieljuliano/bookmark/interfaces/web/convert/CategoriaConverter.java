package br.com.ezequieljuliano.bookmark.interfaces.web.convert;

import br.com.ezequieljuliano.bookmark.domain.model.Categoria;
import br.com.ezequieljuliano.bookmark.domain.service.CategoriaService;
import br.com.ezequieljuliano.bookmark.infrastructure.support.web.convert.BaseEntityConverter;
import org.springframework.stereotype.Component;

import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(forClass = Categoria.class, value = "categoriaConverter")
public class CategoriaConverter extends BaseEntityConverter<Categoria, CategoriaService> {

}
