package fr.quintipio.modelArchiSpringMvc.dao;


import fr.quintipio.modelArchiSpringMvc.model.User;

import java.util.List;

/**
 * Interface pour la gestion en base des utilisateurs
 */
public interface UserDao {

    /**
     * Recherche un utilisateur avec son id
     * @param id l'id de l'utilisateur
     * @return l'utilisateur trouvé
     */
    User findById(int id);

    /**
     * Recherche un utilisateur par son nom d'utilisateur
     * @param sso le login
     * @return l'utilisateur
     */
    User findBySSO(String sso);

    /**
     * ne sert plus !!! recherche un utilisateur par son adresse email
     * @param email l'adresse email
     * @return l'utilisateur
     */
    User findByEmail(String email);

    /**
     * Sauvegarde un utilisateur
     * @param user l'utilisateur à sauvegarder
     */
    void save(User user);

    /**
     * Supprime un utilisateur
     * @param id l'id de l'utilisateur à supprimer
     */
    void deleteById(Integer id);

    /**
     * Retourne tout les utilisateurs en base
     * @return la liste des utilisateurs
     */
    List<User> findAllUsers();
}
