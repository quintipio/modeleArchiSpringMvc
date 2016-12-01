package fr.quintipio.modelArchiSpringMvc.service;


import fr.quintipio.modelArchiSpringMvc.model.User;

import java.util.List;

/**
 * Service pour la gestion des utilisateurs
 */
public interface UserService {

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
    void saveUser(User user);

    /**
     * Met à jour un utilisateur
     * @param user l'utilisateur à updater
     */
    void updateUser(User user);

    /**
     * Supprime un utilisateur
     * @param id l'id de l'utilisateur à supprimer
     */
    void deleteUserById(Integer id);

    /**
     * Retourne tout les utilisateurs en base
     * @return la liste des utilisateurs
     */
    List<User> findAllUsers();

    /**
     * Vérifie si un nom d'utilisateur est déjà présent en base
     * @param id l'id de l'utilisateur à exclure
     * @param sso le nom à rechercher
     * @return true si existe déjà
     */
    boolean isUserSSOUnique(Integer id, String sso);

    /**
     * Vérifie si un mail d'utilisateur est déjà présent en base
     * @param id l'id de l'utilisateur à exclure
     * @param email le mail à rechercher
     * @return true si existe déjà
     */
    boolean isUserEmailUnique(Integer id, String email);

}
