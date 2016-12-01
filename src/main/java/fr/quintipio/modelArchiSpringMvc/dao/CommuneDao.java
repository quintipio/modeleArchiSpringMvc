package fr.quintipio.modelArchiSpringMvc.dao;

import fr.quintipio.modelArchiSpringMvc.model.Commune;

import java.util.List;

/**
 * interface pour la gestion en base des communes
 */
public interface CommuneDao {

    /**
     * Retourne une lsite des communes existantes
     * @return la liste des communes
     */
    public List<Commune> findAllCommune();

    /**
     * Cherche une commune à partir de son ID
     * @param idCommune l'id de la commune
     * @return la commune trouvée
     */
    public Commune findCommune(int idCommune);

}
