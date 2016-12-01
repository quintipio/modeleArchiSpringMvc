package fr.quintipio.modelArchiSpringMvc.service;

import fr.quintipio.modelArchiSpringMvc.model.Commune;

import java.util.List;


/**
 * Service de gestion des communes
 */
public interface CommuneService {

    /**
     * Retourne une lsite des communes existantes
     * @return la liste des communes
     */
    List<Commune> findAllCommune();

    /**
     * Cherche une commune à partir de son ID
     * @param idCommune l'id de la commune
     * @return la commune trouvée
     */
    Commune findCommune(int idCommune);
}
