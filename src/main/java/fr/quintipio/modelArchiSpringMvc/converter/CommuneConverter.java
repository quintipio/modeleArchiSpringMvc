package fr.quintipio.modelArchiSpringMvc.converter;

import fr.quintipio.modelArchiSpringMvc.model.Commune;
import fr.quintipio.modelArchiSpringMvc.service.CommuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter pour gérer les communes dans les vues
 */
@Component
public class CommuneConverter implements Converter<Object, Commune> {

    static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

    @Autowired
    CommuneService communeService;

    public Commune convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        Commune profile= communeService.findCommune(id);
        logger.info("Commune : {}",profile);
        return profile;
    }
}
