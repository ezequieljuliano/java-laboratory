package br.com.ezequieljuliano.bookmark.application.faces.converter.bookmark;

import br.com.ezequieljuliano.bookmark.application.faces.converter.EntityConverter;
import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Categoria;
import br.com.ezequieljuliano.bookmark.domain.service.bookmark.CategoriaService;
import jakarta.faces.convert.FacesConverter;
import org.springframework.stereotype.Component;

@Component
@FacesConverter(forClass = Categoria.class, value = "categoriaConverter")
public class CategoriaConverter extends EntityConverter<Categoria, CategoriaService> {

}
