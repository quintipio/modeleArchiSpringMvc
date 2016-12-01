package fr.quintipio.modelArchiSpringMvc.service;

import fr.quintipio.modelArchiSpringMvc.model.UserProfile;

import java.util.List;

/**
 * Interface pour la partie des roles
 */
public interface UserProfileService {

    /**
     * Retoune un role à partir de son id
     * @param id l'id du role
     * @return le role
     */
    UserProfile findById(int id);

    /**
     * Retourne un role à partir de son nom
     * @param type le nom du role
     * @return le role
     */
    UserProfile findByType(String type);

    /**
     * Retourne tout les roles exsitants
     * @return la liste des roles
     */
    List<UserProfile> findAll();
}
