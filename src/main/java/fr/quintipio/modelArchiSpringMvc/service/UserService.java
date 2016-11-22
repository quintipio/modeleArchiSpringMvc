package fr.quintipio.modelArchiSpringMvc.service;


import fr.quintipio.modelArchiSpringMvc.model.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findBySSO(String sso);

    User findByEmail(String email);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Integer id);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

}
