package fr.quintipio.modelArchiSpringMvc.dao;

import fr.quintipio.modelArchiSpringMvc.model.UserProfile;

import java.util.List;

/**
 * Interface de gestion des roles en base
 */
public interface UserProfileDao {

        /**
         * Retourne tout les roles exsitants
         * @return la liste des roles
         */
        List<UserProfile> findAll();

        /**
         * Retourne un role à partir de son nom
         * @param type le nom du role
         * @return le role
         */
        UserProfile findByType(String type);

        /**
         * Retoune un role à partir de son id
         * @param id l'id du role
         * @return le role
         */
        UserProfile findById(int id);
}
