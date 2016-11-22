package fr.quintipio.modelArchiSpringMvc.dao;

import fr.quintipio.modelArchiSpringMvc.model.UserProfile;

import java.util.List;

public interface UserProfileDao {

        List<UserProfile> findAll();

        UserProfile findByType(String type);

        UserProfile findById(int id);
}
