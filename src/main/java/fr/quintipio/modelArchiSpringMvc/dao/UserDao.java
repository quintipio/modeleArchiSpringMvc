package fr.quintipio.modelArchiSpringMvc.dao;


import fr.quintipio.modelArchiSpringMvc.model.User;

import java.util.List;

public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    User findByEmail(String email);

    void save(User user);

    void deleteById(Integer id);

    List<User> findAllUsers();
}
