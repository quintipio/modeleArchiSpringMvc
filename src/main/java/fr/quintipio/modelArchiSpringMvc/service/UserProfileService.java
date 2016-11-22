package fr.quintipio.modelArchiSpringMvc.service;

import fr.quintipio.modelArchiSpringMvc.model.UserProfile;

import java.util.List;

/**
 * Interface pour la partie des roles
 */
public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();
}
